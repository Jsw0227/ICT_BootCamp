//UpboardDetail.tsx
import React, { useEffect, useState } from 'react'
import style from './upboard.module.css';
import { Link, useParams } from 'react-router-dom';
import axios from 'axios';
import UpBoardComm from './UpBoardComm';
//1) 서버측 데이터의 json을 가지고 인터페이스를 정의한다.
// {
//   "num": 433,
//   "title": "테스트 제목60",
//   "writer": "파워쌤",
//   "content": "테스트 내용입니다60",
//   "imgn": "sample2.png",
//   "hit": 3,
//   "reip": "192.168.0.6",
//   "bdate": "2024-10-30 13:12:56",
//   "mfile": null
// }
//2) 비동기식으로 받은 데이터를 저장할 useState에 자료형으로 선언및 생성
// const [upboard, setUpboard] = useState<UpboardDetail | null>(null);
//3) 상세보기는 반드시 어디를 거쳐서 랜더링이 될까?  즉, 서버로 보낼 num받아줄 useParams로 정의 
// App.tsx에서 라우터가  path='/upboard/:num'  , 리스트에서 상세보기로 가는 <Link to={`/upboard/${item.num}`}>
// 파라미터를 받기위한 훅 => const { num } = useParams<{ num: string }>();
//4) 즉 컴포넌트가 로딩이 될때 서버에서 데이터를 비동기식으로 받아와서 setUpboard에 저장 
// useEffect(()=>{},[]) 에서 구현해야 한다.
//5) aync () => {  const response = await axios.get/post .....}
//6) UI에 적절한 값을 배치하거나 핸들링을 할 수 있다.
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
     //라우터에서 넘어오는 파라미터를 받아서 저장한다.
     const {num} = useParams<{num:string}>();
     //파라미터가 잘 넘어 오는지 테스트 해보기 
     console.log(`num => ${num}`);

     //useEffect(()=>{},[]);
    //[num] 즉 파라미터는 리스트에서 선택될 때마다 값이 바뀌니 그때 마다 데이터를 초기화 한다.
    useEffect(()=>{
        const detailServer = async () => {
            const url = `http://192.168.0.195/myictstudy/upboard/updetail?num=${num}`;
            const resp = await axios.get(url);
            console.log(resp.data);
            //useState에 가져온 데이터를 저장
            setUpboard(resp.data);
        }
        detailServer();
    },[num]);
    const imageBasePath = 'http://192.168.0.195/myictstudy/imgfile/';
    return (
        <div className={style.container}>
            <h1>여기는 UpBoard 상세보기 </h1>
            <table className={style.boardTable}>
                <tbody>
                    <tr>
                        <th>번호</th>
                        <td>{upboard?.num}</td>
                    </tr>
                    <tr>
                        <th>제목</th>
                        <td>{upboard?.title}</td>
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td>{upboard?.writer}</td>
                    </tr>
                    <tr>
                        <th>이미지</th>
                        <td>{upboard?.imgn && (
                            <img src={`${imageBasePath}${upboard.imgn}`} alt={upboard.title}
                            className="img-fluid mt-2" 
                            />
                        )}</td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td>{upboard?.content}</td>
                    </tr>
                    
                </tbody>
               {/* tfoot 목록으로 가는 링크를 만들고 스타일은 .button  */}
                <tfoot>
                    <tr>
                        <td colSpan={2} style={{textAlign:"center"}}>
                        <button className={style.button} >삭제</button>&nbsp;  
                        <Link to="/community/uplist" className={style.button}>
                        목록</Link>
                        </td>
                    </tr>
                </tfoot>
            </table>
            <hr/>
            <UpBoardComm  num={num}/>
        </div>
    )
}

export default UpboardDetail