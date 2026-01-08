import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import styles from './gallery.module.css';
import axios from 'axios';
const galleryItems = [
  { id: 1, title: '바다', image: 'images/bg1.png' },
  { id: 2, title: '산', image: 'images/bg2.png' },
  { id: 3, title: '도시', image: 'images/bg3.png' },
  { id: 4, title: '숲', image: 'images/bg4.png' },
  { id: 5, title: '숲', image: 'images/bg4.png' }
];
//http://192.168.0.31/myictstudy0521/gallery/galleryList
/*
{
  "totalItems": 18,
  "startPage": 1,
  "data": [
    {
      "HIT": 0,
      "ROW_NUM": 1,
      "NUM": 18,
      "WRITER": "테스형",
      "TITLE": "새로운",
      "IMAGENAME": "karina01.jpg",
      "REIP": "192.168.0.31",
      "GDATE": "2025-06-02T05:51:12.000+00:00",
      "CONTENTS": "음하하"
    },
    {
      "HIT": 0,
      "ROW_NUM": 2,
      "NUM": 17,
      "WRITER": "테스형",
      "TITLE": "새로운",
      "IMAGENAME": "car1.jpg",
      "REIP": "192.168.0.31",
      "GDATE": "2025-06-02T05:41:04.000+00:00",
      "CONTENTS": "음하하"
    },
    ...
      ],
  "totalPages": 2,
  "endPage": 2,
  "currentPage": 1
}
*/
//여기서 키가 대문자인 이유는 back에서 
//<select id="list" resultType="map" parameterType="map">  resultType="map" 일 경우 
interface GalleryVO {
  NUM: number;
  TITLE: string;
  WRITER: string;
  CONTENTS: string;
  REIP?: string;
  HIT?: number;
  GDATE?: string;
  //https://developer.mozilla.org/ko/docs/Web/API/File
  //File Interface는 javascript에서 파일을 접근할 수 있는 자바스크립트 객체이다.
  // images: File | null; 
  IMAGENAME: string;
}
//totalItems - count
//totalPages - 전체페이지수
//currentPage - 현재페이지 
//startPage , endPage
const Gallery: React.FC = () => {
  const [galleryList, setGalleryList] = useState<GalleryVO[]>([]);
  const [totalItems, setTotalItems] = useState(0); //전체 갯수
  const [totalPages, setTotalPages] = useState(0); //전체 페이지
  const [currentPage, setCurrentPage] = useState(1); // 기본 1값을 초기화
  const [startPage, setStartPage] = useState(1); // 시작
  const [endPage, setEndPage] = useState(1); //끝
  //<검색>을 위한 useState를 추가한다.
  const [searchType, setSearchType] = useState('1'); //검색 타입
  const [searchValue, setSearchValue] = useState(''); //검색 값 
  const imageBasePath = 'http://192.168.0.195/myictstudy/imgfile/gallery/';

  const fetchGalleryList = async (page: number) => {
    try {
    const response = await axios.get('http://192.168.0.195/myictstudy/gallery/galleryList',
        {
          params: { cPage: page, searchType: searchType, searchValue: searchValue }
        });
      setGalleryList(response.data.data);//useState에 저장
      setTotalItems(response.data.totalItems);
      setTotalPages(response.data.totalPages);
      setCurrentPage(response.data.currentPage);
      setStartPage(response.data.startPage);
      setEndPage(response.data.endPage);
    } catch (error) {
      console.error("데이터 가져오기 실패:" + error);
    }
  }
  
  useEffect(() => {
    // 서버에서 목록을 가져오는 함수
    // async () => { await .... }
    fetchGalleryList(currentPage);
  }, [currentPage]);

  //page Handler 
  const pageChange = (page: number) => {
    setCurrentPage(page);
  }
  const searchFunction = () => {
    fetchGalleryList(1);
  }



  return (
    <div className={styles.container}>
      <h2 className={styles.title}>갤러리 {totalItems}</h2>
      <div style={{ textAlign: 'right', marginBottom: '15px' }}>
        <Link to="/gallery/write" className={styles.button}>이미지 추가</Link>
      </div>
      <div className={styles.grid}>
        {
        galleryList.map(item => (
          <Link to={`/gallery/gdetail/${item.NUM}`} key={item.NUM} 
                                  style={{ textDecoration: 'none' }}>
            <div className={styles.card}>
              <div className={styles.cardTitle}>{item.NUM}
            //{ imageBasePath+ item.IMAGENAME}

              </div>
              <img src={imageBasePath + item.IMAGENAME} alt={item.TITLE} />
              <div className={styles.cardTitle}>{item.TITLE}</div>
            </div>
          </Link>
        ))
        }
      </div>
      <div>





        {/* 검색 */}
        <select
          onChange={(e) => { setSearchType(e.target.value) }}>
          <option value="1">작성자</option>
          <option value="2">제목</option>
          <option value="3">내용</option>
        </select>
        <input type='text'
          onChange={(e) => { setSearchValue(e.target.value) }} />
        <button className="btn btn-warning" onClick={searchFunction}>검색</button>
      </div>
      <div>
        <nav>
          <ul className="pagination justify-content-center">
            {/* PrevPage 출력하기 : startPage > 1 보다 클때   
                                        Upboard List = 73
                                        검수용 : totalPages 8 / startPage: 6 / endPage : 8
                                    */}
            {startPage > 1 && (
              <li className="page-item">
                <button className="page-link" onClick={() => { pageChange(startPage - 1) }}>이전</button>
              </li>
            )}


            {/* 페이지 출력하기 */}
            {
              // startPage = 1 , endPage=3 => [1,2,3]이란 배열을 만들어 준다.
              Array.from({ length: endPage - startPage + 1 }, (xx, i) => i + startPage)
                .map((page) => (
                  <li key={page} className={`page-item ${page === currentPage ? 'active' : ''}`}>
                    <button className="page-link" onClick={() => { pageChange(page) }}>{page}</button>
                  </li>
                ))
            }


            {/* <li className="page-item active">
                                        <button className="page-link">2</button>
                                    </li> */}

            {/* NextPage 출력하기 : totalPage 보다 endPage 적을 때 다음페이지가 있는 것으로 계산  
                                검수용 : totalPages 8 / startPage: 1 / endPage : 5
                                */}
            {endPage < totalPages && (
              <li className="page-item">
                <button className="page-link" onClick={() => { pageChange(endPage + 1) }}>다음</button>
              </li>
            )}

          </ul>
        </nav>
      </div>
    </div>
  );
};

export default Gallery;