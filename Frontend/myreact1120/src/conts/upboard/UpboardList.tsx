// UpboardList.tsx
import React, { useEffect, useState } from 'react'
import style from './upboard.module.css'
import { Link } from 'react-router-dom'
import axios from 'axios';

// 자바스크립트 인터페이스를 선언
interface UpBoardVO {
    num: string;
    title: string;
    writer: string;
    content: string;
    imgn: string;
    hit: number;
    reip: string;
    bdate: string;
}
//totalItems - count
//titalPages - 전체 페이지 수
//currentPage - 현재 페이지
//startPage , endPage

const UpboardList: React.FC = () => {

    // 2. 서버 데이터 JsonArray를 자바스크립트 배열로 저장할 useState 만들기 
    const [upboardList, setUpboardList] = useState<UpBoardVO[]>([]);
    const [totalItems, setTotalItems] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [currentPage, setCurrentPage] = useState(1);
    const [startPage, setStartPage] = useState(1);
    const [endPage, setEndPage] = useState(1);
    const pagePerBlock = 5;
    
    // 3. 초기화 시 axios를 사용해서 서버측 데이터를 받아와 useState에 저장
    // http://192.168.0.22/myictstudy/imgfile/ex1.png
    const imageBasePath = 'http://192.168.0.13/myictstudy/imgfile/';

    // 4. useEffect를 사용해서 서버로 비동기식으로 접속하여 데이터를 가져오는 설정
    useEffect(() => {
    const fetchUpboardList = async (page:number) => {
        try {
            // url에서 테스트한 주소를 복사
            // 서버에 파라미터를 Map으로 전달 한다. 자바스크립트 Object 리터럴 {key,value}
            // => @RequestParam Map<String, String> paramMap
            const urls ='http://192.168.0.13/myictstudy/upboard/upList'
            const response = await axios.get(urls,{params:{cPage:page}})
            console.log(response.data);
            setUpboardList(response.data.data);
        } catch (error) {
            console.error("데이터 가져오기 실패: " + error);
        }
    };
    fetchUpboardList(currentPage);
}, []);


  return (
    <div className={style.container}>
        <h2>UpboardList</h2>
        <table className={style.boardTable}>
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>이미지</th>
                    <th>조회수</th>
                    <th>작성일</th>
                </tr>
            </thead>

            <tbody>
                {
                    upboardList.map((item) => (
                    <tr key={item.num}>
                        <td>{item.num}</td>
                        <td>
                            <Link to={`/board/${item.num}`} className={style.titleLink}>
                                {item.title}
                            </Link>
                        </td>
                        <td>{item.writer}</td>

                        <td>{item.imgn ? (
                            <img src={`${imageBasePath}${item.imgn}`} alt={item.title} style={{width:'80px', height:'auto'}}/>
                        ) : ("No Image")}</td>
                        <td>{item.hit}</td>
                        <td>{item.bdate}</td>
                    </tr>   
                ))}      
            </tbody>

            <tfoot>
                <tr>
                    <td colSpan={6} style={{textAlign: "center"}}>
                        <Link to="/community/upform" className={style.button}>
                            글쓰기
                        </Link>
                    </td>    
                </tr>    
            </tfoot>    
            
        </table>
    </div>
  )
}

export default UpboardList