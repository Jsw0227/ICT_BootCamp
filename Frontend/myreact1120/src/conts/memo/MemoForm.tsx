//MemoForm.tsx
import React, { useState } from 'react'
import styles from '../upboard/upboard.module.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
interface MemoVO {
    num?: number;
    writer: string;
    conts: string;
    mreip?: string;
    mdate?: string;
}
const MemoForm: React.FC = () => {

    //useState 선언 및 초기화 =? 만 빼고 초기화
    const [formData, setFormData] = useState<MemoVO>({
        //mreip: '',
        writer: '',
        conts: '',
    });
    // 입력후 리스트로 이동 하기 위한 훅을 생성
    const navigate = useNavigate();
    const formChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        //선택된 target 즉, HTMLElement에서 name,value를 각각 받아 온다.
        const { name, value } = e.target
        // 받아온 데이터를 useState의 formData 객체에 각각 저장한다.
        setFormData({ ...formData, [name]: value })
    }
    const myFormSubmit = async (e: React.FormEvent) => {
        e.preventDefault();// 폼 전송을 막기 
        const url = 'http://192.168.0.195/myictstudy/mymemo/add';
        try {
            await axios.post(url, formData, {
                headers: { 'Content-Type': 'application/json' }
            });
            alert("메모가 등록되었습니다!");
            navigate('/community/memolist');
        } catch (error) {
            console.error("등록 실패:", error);
        }
    }
    return (
        <div className={styles.container}>
            <h2 className={styles.title}>이미지 등록 예제</h2>
            <form onSubmit={myFormSubmit} className={styles.form}>
                <table className={styles.boardTable}>
                    <tbody>
                        <tr>
                            <th>작성자</th>
                            <td>
                                <input type="text" name="writer" id="writer"
                                    className={styles.input}
                                    onChange={formChange} required
                                /></td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td>
                                <textarea name="conts" id="conts"
                                    style={{ width: '90%', height: '150px', padding: '8px' }}
                                    onChange={formChange} required
                                />
                            </td>
                        </tr>
                    </tbody>
                     <tfoot>
                    <tr>
                        <th colSpan={2}>
                            <button type="submit" className={styles.button}>등록하기</button>
                        </th>
                    </tr>

                </tfoot>
                </table>
            </form>
        </div>
    )
}

export default MemoForm