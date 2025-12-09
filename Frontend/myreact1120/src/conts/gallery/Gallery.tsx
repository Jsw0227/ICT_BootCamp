import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import styles from './gallery.module.css';
import axios from 'axios';
// const galleryItems = [
//   { id: 1, title: '바다', image: 'images/bg1.png' },
//   { id: 2, title: '산', image: 'images/bg2.png' },
//   { id: 3, title: '도시', image: 'images/bg3.png' },
//   { id: 4, title: '숲', image: 'images/bg4.png' },
//   { id: 5, title: '숲', image: 'images/bg4.png' }
// ];
interface GalleryVO {
  NUM: number;
  TITLE: string;
  WRITER: string;
  CONTENTS: string;
  REIP?: string;
  HIT?: number;
  GDATE?: string;
  IMAGENAME: string;
}
const Gallery: React.FC = () => {

  const [galleryList, setGalleryList] = useState<GalleryVO[]>([]);
  const [totalItems, setTotalItems] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [currentPage, setCurrentPage] = useState(1);
  const [startPage, setStartPage] = useState(1);
  const [endPage, setEndPage] = useState(1);
  const pagePerBlock = 5;
  const [searchType, setSearchType] = useState('1');
  const [searchValue, setSearchValue] = useState('');
  const imageBasePath = 'http://192.168.0.24/myictstudy/imgfile/gallery/';

  const fetchGalleryList = async (page: number) => {
    try {
      const response = await axios.get('http://192.168.0.24/myictstudy/gallery/galleryList', { params: { cPage: page, searchType: searchType, searchValue: searchValue } });
      setGalleryList(response.data.data);
      setTotalPages(response.data.totalPages);
      setTotalItems(response.data.totalItems);
      setCurrentPage(response.data.currentPage);
      setStartPage(response.data.startPage);
      setEndPage(response.data.endPage);

    } catch (error) {
      console.log("데이터 가져오기 실패:" + error);
    }
  };

  useEffect(() => {
    fetchGalleryList(currentPage);
  }, [currentPage]);

  const pageChange = (page: number) => {
    setCurrentPage(page);
  }

  const searchFunction = () => {
    fetchGalleryList(1);
  }



  return (
    <div className={styles.container}>
      <h2 className={styles.title}>갤러리</h2>
      <div style={{ textAlign: 'right', marginBottom: '15px' }}>
        <Link to="/gallery/write" className={styles.button}>이미지 추가</Link>
      </div>
      <div className={styles.grid}>
        {galleryList.map(item => (
          <Link to={`/gallery/gdetail/${item.NUM}`} key={item.NUM} style={{ textDecoration: 'none' }}>
            <div className={styles.card}>
              <div className={styles.cardTitle}>{item.NUM}</div>
              <img src={imageBasePath + item.IMAGENAME} alt={item.TITLE} />
              <div className={styles.cardTitle}>{item.TITLE}</div>
            </div>
            
          </Link>
        ))}
        </div>
        {
          Array.from({length: endPage - startPage + 1}, (xx,i) => i+startPage).map((page)=>(
            <li key={page}>
              <button onClick={()=>{pageChange(page)}}>{page}</button>
            </li>
          ))
        }
    </div>
  );
};

export default Gallery;