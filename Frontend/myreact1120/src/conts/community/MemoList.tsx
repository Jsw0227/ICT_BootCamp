import React, { useEffect, useState } from 'react'
import style from './upboard.module.css'
import { Link } from 'react-router-dom'
import axios from 'axios';

// 자바스크립트 인터페이스를 선언
interface memoVO {
    num: string;
    title: string;
    writer: string;
    conts: string;
    mreip: string;
    mdate: string;
}
const MemoList: React.FC = () => {

    const [memoList, setMemoList] = useState<memoVO[]>([]);
    const [totalItems, setTotalItems] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [currentPage, setCurrentPage] = useState(1);
    const [startPage, setStartPage] = useState(1);
    const [endPage, setEndPage] = useState(1);
    const pagePerBlock = 5;


    const pageChange = (page: number) => {
        setCurrentPage(page);
    }


    return (
       <div className={style.container}>
            <h2>UpboardList</h2>
           {/* <h3> 검수용 : totalPages {totalPages} / startPage {startPage} / endPage {endPage}</h3> */}
            <table className={style.boardTable}>
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>작성자</th>
                        <th>내용</th>
                        <th>아이피</th>
                        <th>작성일</th>
                    </tr>
                </thead>

                <tbody>
                    {
                        memoList.map((item) => (
                            <tr key={item.num}>
                                <td>{item.num}</td>
                                <td>
                                    {item.writer}
                                </td>
                                <td>{item.conts}</td>
                                <td>{item.mreip}</td>
                                <td>{item.mdate}</td>
                            </tr>
                        ))}
                </tbody>

 <tfoot>
                    <tr>
                        <td colSpan={5} style={{ textAlign: "center" }}>
                              <nav>
                                <ul className="pagination justify-content-center">
                                    {startPage > 1 && (
                                        <li className="page-item">
                                            <button className="page-link" 
                                            onClick={()=>{ pageChange(startPage - 1)}}>
                                                이전</button>
                                        </li>
                                    )}
                                     {/* 페이지 출력하기 */}
                                     {
                                         // startPage = 1 , endPage=3 => [1,2,3]이란 배열을 만들어 준다.
                                         Array.from({ length: endPage - startPage + 1 }, (xx, i) => i + startPage)
                                         .map((page)=>(
                                            <li key={page} className={`page-item ${page === currentPage?'active':''}`}>
                                                 <button className="page-link" onClick={()=>{ pageChange(page)}}>{page}</button>   

                                            </li>
                                         ))
                                     }

                                {/*
                                NextPage 출력하기 : totalPage 보다 endPage 적을 때 다음페이지가 
                                있는 것으로 계산        
                                */}
                                    {endPage < totalPages && (
                                        <li className="page-item">
                                            <button className="page-link" onClick={()=>{ pageChange(endPage + 1)}}>
                                                다음</button>
                                        </li>
                                    )}
                                </ul>
                              </nav>



                            {/* UpBoardForm.tsx  */}
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

export default MemoList