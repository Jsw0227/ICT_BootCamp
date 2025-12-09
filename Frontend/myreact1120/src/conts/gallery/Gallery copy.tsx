import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import styles from './gallery.module.css';
import { galleryItems } from './galleryData';
// const galleryItems = [
//   { id: 1, title: '바다', image: 'images/bg1.png' },
//   { id: 2, title: '산', image: 'images/bg2.png' },
//   { id: 3, title: '도시', image: 'images/bg3.png' },
//   { id: 4, title: '숲', image: 'images/bg4.png' },
//   { id: 5, title: '숲', image: 'images/bg4.png' }
// ];
// {
//   "totalItems": 12,
//   "startPage": 1,
//   "data": [
//     {
//       "HIT": 0,
//       "ROW_NUM": 1,
//       "NUM": 15,
//       "WRITER": "asd",
//       "GALLERYID": 15,
//       "TITLE": "asd",
//       "IMAGENAME": "images (1).jpg",
//       "REIP": "192.168.0.24",
//       "GDATE": "2025-12-09T03:23:27.000+00:00",
//       "CONTENTS": "asdasd"
//     },
//키가 대문자인 이유는 백엔드에서 resultType="map" 일 경우



const Gallery: React.FC = () => {



  return (
    <div className={styles.container}>
      <h2 className={styles.title}>갤러리</h2>
      <div style={{ textAlign: 'right', marginBottom: '15px' }}>
        <Link to="/gallery/write" className={styles.button}>이미지 추가</Link>
      </div>
      <div className={styles.grid}>
        {galleryItems.map(item => (
          <Link to={`/gallery/${item.id}`} key={item.id} style={{ textDecoration: 'none' }}>
            <div className={styles.card}>
              <img src={item.image} alt={item.title} />
              <div className={styles.cardTitle}>{item.title}</div>
            </div>
          </Link>
        ))}
      </div>
    </div>
  );
};

export default Gallery;