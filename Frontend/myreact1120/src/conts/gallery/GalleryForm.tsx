import React, { useState } from 'react'
import styles from './gallery.module.css';
const GalleryForm: React.FC = () => {
    const [form, setForm] = useState({
        id: 0,
        title: '',
        image: '',
    })
    const [title, setTitle] = useState('');
    const [image, setImage] = useState('');
    const [nextId, setNextId] = useState(1);
    const [errors, setErrors] = useState<{ [key: string]: string }>({});
    // 유효성을 체크하는 함수 정의
    const validate = () => {
        const newErrors: { [key: string]: string } = {};
        if (!title) newErrors.title = 'title을 입력하세요';
        if (!image) newErrors.image = 'img url을 입력하세요';
        return newErrors;
    }
    const gallerySubmit = (e: React.FormEvent) => {
        e.preventDefault();
        const myErrors = validate();
        setNextId(nextId+1);
        
        if (Object.keys(myErrors).length > 0) {
            console.log(`Object.keys(myErrors) => ${Object.keys(myErrors)}`);
            setErrors(myErrors);
            return; 
        }else{
           setErrors({image:''})
        }
        const gObj = { id: nextId, title, image };
        setForm(gObj);
         setNextId(nextId + 1);
        // 서버로 전송 
            console.log('데이터 전송:', gObj);
        alert(`서버로 데이터 전송 : ${gObj.title} / ${gObj.image}`);
        
    }
    return (
             <div className={styles.container}>
            <h2 className={styles.title}>이미지 등록</h2>
            <form className={styles.form} onSubmit={gallerySubmit}>
                <input
                    className={styles.input}
                    type="text"
                    placeholder="제목 입력"
                    value={title}
                    onChange={e => setTitle(e.target.value)}
                />
                {errors.title && <p className={styles.error}>{errors.title}</p>}

                <input
                    className={styles.input}
                    type="text"
                    placeholder="이미지 URL 입력"
                    value={image}
                    onChange={e => setImage(e.target.value)}
                />
                {errors.image && <p className={styles.error}>{errors.image}</p>}

                <button type="submit" className={styles.button}>등록</button>
            </form>
        </div>
    )
}

export default GalleryForm