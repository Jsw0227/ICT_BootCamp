//GalleryDetail.tsx
import React from 'react';
import { useParams } from 'react-router-dom';
import styles from './gallery.module.css';
import { galleryItems } from './galleryData';

const GalleryDetail: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const item = galleryItems.find(it => it.id === Number(id));
  alert(item?.image);
  if (!item) {return <p>이미지를 찾을 수 없습니다.</p>;}
  return (
    <div className={styles.container}>
      <h2 className={styles.title}>{item.title}</h2>
      <div className={styles.detail}>
        <img src={item?.image} alt={item.title} />
      </div>
    </div>
  );
};
export default GalleryDetail;
