import React, { useEffect, useState } from 'react'
import style from './upboard.module.css';
import { useParams } from 'react-router-dom';
import { Link } from 'react-router-dom'
import axios from 'axios';
import UpBoardComm from './UpBoardComm';
interface UpBoardVO {
    num: number;
    title: string;
    writer: string;
    content: string;
    imgn?: string;
    hit?: number;
    reip?: string;
    bdate?: string;
    mfile: File | null;
}

const UpboardDetail: React.FC = () => {

    
    const [upboard,setUpboard] = useState<UpBoardVO|null>(null);
    const {num} = useParams<{num:string}>();
    console.log(`num => ${num}`)
    //[num] 즉 파라미터는 리스트에서 선택될 때마다 값이 바뀌니 그때마다 데이터를 초기화한다.
    useEffect(() => {
        const detailServer = async () => {
            const url = `http://192.168.0.24/myictstudy/upboard/updetail?num=${num}`;
            const resp = await axios.get(url);
            console.log(resp.data);
            setUpboard(resp.data);
        }
        detailServer();
    },[num])
    const imageBasePath = 'http://192.168.0.24/myictstudy/imgfile/';
    return (
        
        <div className={style.container}>
            <h1> 상세보기</h1>
            <table className={style.boardTable}>
                
                <tbody>
                    <tr>
                        <th>번호</th>
                        <td>
                            {upboard?.num}
                        </td>
                    </tr>
                    <tr>
                        <th>제목</th>
                        <td>
                            {upboard?.title}
                        </td>
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td>
                            {upboard?.writer}
                        </td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td>
                            {upboard?.content}
                        </td>
                    </tr>
                    <tr>
                        <th>이미지</th>
                        <td>
                            {upboard?.imgn && (
                                <img src= {`${imageBasePath}${upboard.imgn}`} alt={upboard.title} className='img-fluid mt-2' />
                            )}
                        </td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <td colSpan={2} style={{textAlign:"center"}}>
                            <button className={style.button}>삭제</button>&nbsp;
                           <Link to="/community/uplist" className={style.button}>
                        목록</Link>
                        </td>
                    </tr>

                </tfoot>
            </table>
            <hr />
            <UpBoardComm num={num}/>
        </div>
    )
}

export default UpboardDetail