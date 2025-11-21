//BoardDetail.tsx
import React from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { boardList } from './boardData';
import style from './board.module.css';
import { Link } from 'react-router-dom';
const BoardDetail: React.FC = () => {
    const navigate = useNavigate();
    // path에서 전달되어 온 값을 받아서 처리해주는 Hook
    // useParams()
    // <Route path='/board/:id' => {id}
    //--------파라미터를 받아서 변수에 저장하기 위함 **********   
    const { id } = useParams<{ id: string }>();
    // 파라미터로 받아온 id값과 더미데이터의 id가 같으면 그 객체만 detail에 저장
    // 하겠다.
    // 스토리지에서 기존의 값을 리스트로 저장 
    //TS2345: Argument of type 'string | null' is not assignable to parameter of type 'string'.
    // => null => '[]' 즉 비어 있는 리스트라도 만들어 줘야 함.
    const oriBoardList = JSON.parse(localStorage.getItem("boardList") || '[]');
    const details = oriBoardList.filter((item: any) => item.id === Number(id));
    console.log("******************");
    console.log(`id=>${id}`);
    console.log(`상세보기 => ${typeof (id)} || ${details} `);
    console.log(details);
    //기존에 스토리지의 데이터를 다 가져와서 현재 id값 만 제외하고 다시 json으로 만들어서
    //스토리지에 저장 
    const delBoard = () => {
        if(window.confirm("정말 삭제하겠나요?")){
            const oriBoardList = JSON.parse(localStorage.getItem("boardList") || '[]');
            const newBoardList =oriBoardList.filter((item:any) => item.id !== Number(id))
            localStorage.setItem('boardList',JSON.stringify(newBoardList));
            alert('삭제 되었습니다.');
            navigate("/board");
        }
    }

    return (
        <div className={style.container}>
            <h2>게시글 상세페이지</h2>
            <table className={style.boardTable}>
                <tbody>
                    <tr>
                        <th>제목</th><td>{details[0]?.title}</td>
                    </tr>
                    <tr>
                        <th>작성자</th><td>{details[0]?.writer}</td>
                    </tr>
                    <tr>
                        <th>내용</th><td>{details[0]?.content}</td></tr>
                </tbody>
                {/* tfoot 목록으로 가는 링크<Link ...>를 만들고 스타일은 .button  */}
                <tfoot>
                    <tr>
                        <td colSpan={2} style={{ textAlign: "center" }}>
                            <button className={style.button} onClick={delBoard}>삭제</button>  
                            <Link to="/board" className={style.button}>목록으로</Link>
                        </td>
                    </tr>
                </tfoot>

            </table>
        </div>
    )
}
export default BoardDetail