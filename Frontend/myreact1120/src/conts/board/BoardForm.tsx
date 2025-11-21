//BoardForm.tsx
import { Link, useNavigate } from 'react-router-dom';
import style from './board.module.css';
import React, { useState } from 'react'

const BoardForm: React.FC = () => {
    // form에서 입력받을 상태값을 저장할 useState
    const [title, setTitle] = useState('');
    const [writer, setWriter] = useState('');
    const [content, setContent] = useState('');
    //useNavigate() ; navigate('path')
    //window.location='path'; window.href='path';
    const navigete = useNavigate();

    const boardSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();//폼 막자
        alert(`${title}, ${writer} ${content}`);
        console.log('새 글 등록:', { title, writer, content });
        //새로운 게시판 데이터를 json Object 형식으로 변환 
        const newBoard = {
            id: Date.now(),//초단위로 알아서 증가 해주기 때문에 그냥 식별용
            title,
            writer,
            content
        }
        //기존 스토리지에서 읽어와서 
        const boardList = localStorage.getItem('boardList');
        // 스토리지에 값이 있다면 배열로 반환,아니면 비어있는 배열로 반환
        //JSON.parse(boardList) =>  [{"id":11213,"title":"오늘은","writer":"테스형"}]
        //list =  [{id:11213,title:"오늘은",writer:"테스형"}] 
        const list = boardList ? JSON.parse(boardList) : [];
        //배열에 특정값을 저장 함수 push()
        //저장 : list.push({id:11222,title:"내일일2은",writer:"테스형2"})
        //리스트 형태 : list = [{id:11213,title:"오늘은",writer:"테스형"},
        //  {id:11222,title:"내일일2은",writer:"테스형2"}]
        list.push(newBoard);
        //localStorage 저장 setItem("key",값) 할 때 jsonObject로 넣어준다고 했으니
        //어떤 메서드를 사용해서 자바스크립트의 obj -> jsonObject ??
        //JSOM.stringify(값)
        localStorage.setItem('boardList',JSON.stringify(list));
        alert("Save했어요");
        navigete("/board"); //board 즉 리스트로 강제로 이동해준다.
    }
    return (
        <div className={style.container}>
            <h2>글쓰기</h2>
            <form onSubmit={boardSubmit}>
                <table className={style.boardTable}>
                    <tbody>
                        <tr>
                            <th>제목</th>
                            <td><input type="text" name="title" id="title"
                                style={{ width: '100%', padding: '8px' }}
                                onChange={e => { setTitle(e.target.value) }}
                            /></td>
                        </tr>
                        <tr>
                            <th>작성자</th>
                            <td><input type="text" name="writer" id="writer"
                                style={{ width: '100%', padding: '8px' }}
                                onChange={e => { setWriter(e.target.value) }}
                            /></td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td><textarea name="content" id="content"
                                style={{ width: '100%', height: '150px', padding: '8px' }}
                                onChange={e => { setContent(e.target.value) }}
                            /></td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th colSpan={2}>
                                <button type="submit" className={style.button}>등록하기</button>
                                <Link to="/board" className={style.button}
                                    style={{ marginLeft: '10px' }}
                                >취소</Link>
                            </th>
                        </tr>

                    </tfoot>
                </table>
            </form>
        </div>
    )
}

export default BoardForm