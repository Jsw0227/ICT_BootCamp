//MemoList.tsx
import React, { useEffect, useState } from 'react'
import style from '../upboard/upboard.module.css';
import axios from 'axios';
import { Link } from 'react-router-dom';
interface MemoVO {
    num:number;
    writer:string;
    conts:string;
    mreip:string;
    mdate:string;
}
const MemoList: React.FC = () => {
    const [memoList, setmemoList] = useState<MemoVO[]>([]);
    const [totalItems, setTotalItems] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [currentPage, setCurrentPage] = useState(1); // 기본 1값을 초기화
    const [startPage, setStartPage] = useState(1);
    const [endPage, setEndPage] = useState(1);
  useEffect(() => {
        // 서버에서 목록을 가져오는 함수
        // async () => { await .... }
        const fetchMemoList = async (page: number) => {
            try {
                //http://192.168.0.26/myictstudy0521/upboard/upList?cPage=2
                //서버에 파라미터를 Map으로 전달 한다. 자바스크립트 Object 리터럴 {key,value} 
                // => @RequestParam Map<String, String> paramMap
                const response = await axios.get('http://192.168.0.195/myictstudy/mymemo/list',
                    { params: { cPage: page } })
                console.log(response.data.data);
                setmemoList(response.data.data);//useState에 저장
                setTotalItems(response.data.totalItems);
                setTotalPages(response.data.totalPages);
                setCurrentPage(response.data.currentPage);
                setStartPage(response.data.startPage);
                setEndPage(response.data.endPage);
            } catch (error) {
                console.error("데이터 가져오기 실패:" + error);
            }
        }
        fetchMemoList(currentPage);
    }, [currentPage]);
    //page Handler 
    const pageChange = (page:number) => {
        setCurrentPage(page);
    }  
  return (
    <div className={style.container}>
    <h2>Upboard List = {totalItems}</h2>
            <h3>검수용 : totalPages {totalPages} / startPage: {startPage}
                / endPage : {endPage}
            </h3>
          <table className={style.boardTable}>
                <thead>
                    <tr><td colSpan={6}>현재페이지 {currentPage}</td></tr>
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
                                    <Link to={`/board/${item.num}`}
                                        className={style.titleLink}
                                    >{item.writer}</Link>
                                </td>
                                <td>{item.conts}</td>
                                <td>{item.mreip}</td>
                                <td>{item.mdate}</td>
                            </tr>
                        ))
                    }
                </tbody>
                <tfoot>
                    <tr>
                        <th colSpan={6} >
                            <nav>
                                <ul className="pagination justify-content-center">
                                    {/* PrevPage 출력하기 : startPage > 1 보다 클때   
                                        Upboard List = 73
                                        검수용 : totalPages 8 / startPage: 6 / endPage : 8
                                    */}
                                    {startPage > 1 && (
                                        <li className="page-item">
                                            <button className="page-link" onClick={()=>{ pageChange(startPage - 1)}}>이전</button>
                                        </li>
                                    )}


                                    {/* 페이지 출력하기 */}
                                    {
                                        // startPage = 1 , endPage=3 => [1,2,3]이란 배열을 만들어 준다.
                                        Array.from({ length: endPage - startPage + 1 }, (xx, i) => i + startPage)
                                            .map((page) => (
                                                <li key={page} className={`page-item ${page === currentPage?'active':''}`}>
                                                    <button className="page-link" onClick={()=>{ pageChange(page)}}>{page}</button>
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
                                            <button className="page-link" onClick={()=>{ pageChange(endPage + 1)}}>다음</button>
                                        </li>
                                    )}

                                </ul>
                            </nav>
                        </th>
                    </tr>
                    <tr>
                        <td colSpan={6} style={{ textAlign: "center" }}>
                            {/* BoardForm.tsx  */}
                            <Link to="/community/memoForm" className={style.button}>
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