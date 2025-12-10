//GalleryDetail.tsx
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import styles from './gallery.module.css';
import { galleryItems } from './galleryData';
import axios from 'axios';

interface GalleryItem{
  num:number;
  title:string;
  contents:string;
  writer:string;
  reip:string;
  hit:number;
  gdate:string;
  getimglist:string[]|null;
}

const GalleryDetail: React.FC = () => {
  const {num} = useParams<{num:string}>();
  const[item,setItem] = useState<GalleryItem | null>(null);
  const [loading, setLoading] = useState(true);


  const { id } = useParams<{ id: string }>();

  useEffect(()=>{
    const fetchData = async() => {
      if(!num){
        console.error("num 파라미터가 없습니다.")
        setLoading(false);
        return;
      }
      try {
        const url = 'http://192.168.0.24/myictstudy/gallery/gdetail';
        const response = await axios.get(url,{params:{num:parseInt(num)}});
        console.log(response.data)
        setItem(response.data);
      } catch (error) {
        console.error("데이터 요청 실패 :",error)
      } finally{
        setLoading(false);
      }
    }
    fetchData();
  },[num])


  return (
    <div className={styles.container}>
      <h2 className={styles.title}>{item?.title}</h2>
      <div className={styles.detail}>
        <p><strong>작성자 :</strong>{item?.writer}</p>
        <p><strong>내용 :</strong>{item?.contents}</p>
        <p><strong>작성일 :</strong>{item?.gdate}</p>
        <p><strong>조회수 :</strong>{item?.hit}</p>
        <div className={styles.imageBox}>
          {item?.getimglist && item.getimglist.length>0?
          (item.getimglist.map((img,idx)=>(<img key={idx} src={`http://192.168.0.24/myictstudy/imgfile/gallery/${img}`} alt={`img-${idx}`}/>)))   : (<p> 이미지가 없습니다.</p>)
          }

        </div>

  
      </div>
    </div>
  );
};
export default GalleryDetail;
