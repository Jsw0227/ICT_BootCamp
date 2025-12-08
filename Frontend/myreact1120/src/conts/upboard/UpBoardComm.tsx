import axios from 'axios';
import style from './upboard.module.css';
import React, { useEffect, useState } from 'react'

interface UpBoardCommProps {
  num?: string;
}

interface UpBoardCommVO {
  num: number;
  ucode: number;
  uwriter: string;
  ucontent: string;
  reip: string;
  uredate: string;
}

const UpBoardComm: React.FC<UpBoardCommProps> = ({ num }) => {

  //step 7) axios로 json으로 받아온 데이터를 저장할 useState를 정의
  //useState의 자료형은 ? UpBoardCommVO[] => jsonList안에 jsonObject이기 때문에...
  const [comments, setComments] = useState<UpBoardCommVO[]>([]);

  const getComments = async () => {
    try {
      const url = `http://192.168.0.24/myictstudy/upboard/upcommList?num=${num}`;
      const response = await axios.get<UpBoardCommVO[]>(url);
      console.log(response.data);
      setComments(response.data);
    } catch (error) {
      console.log("데이터 로딩 실패!", error);
    }

  }

  useEffect(() => {
    console.log("Num" + num);
    getComments();
  }, [num])

  // 폼에서 입력한 값을 onChange 이벤트가 발생 할 때 마다 값을 저장하기 위한 useState 정의하기
  const [writer, setWriter] = useState("");
  const [content, setContent] = useState("");

  const commentSubmit =  async (e: React.FormEvent) => {
    e.preventDefault();
    console.log(`Writer =>${writer}`);
    console.log(`Content => ${content}`);
    // 서버측에 테이터를 받는 유형을 확인 한 후 지금처럼 @RequestBody 형태 즉, json이면 front에서는 json으로 데이터를 전송한다 ******
    // backend의 함수 첫 줄만 복붙 해보기
    //	@PostMapping("/upcommAdd")
    //public ResponseEntity<?> upBoardComm(@RequestBody UpBoardCommVO vo){

    // useState에서 받아온 데이터를 json화 시키기
    // 이때 키 : 값 일 때 키는 UpBoardCommVO의 property와 같아야한다.
    const commentDate = {
      ucode : num,
      uwriter : writer,
      ucontent : content,
      reip : '192,168,0,13' 
    }
    try {
      //axios.post(url, data, {headers:'Content-Type' : 'application/json'})
      await axios.post(`http://192.168.0.24/myictstudy/upboard/upcommAdd`,commentDate, {headers:{'Content-Type' : 'application/json'}})
      setWriter("");
      setContent("");
      getComments();
    } catch (error) {
      
    }
  }

  return (
    <div className='mt-4'>
      <h4>Comments</h4>
      <form className='mb-3' onSubmit={commentSubmit}>
        <div className='mb-2'>
          <input type="text" placeholder='작성자' className='form0control' onChange={(e) => setWriter(e.target.value)} />
        </div>
        <div className='mb-2'>
          <textarea className='form-control' placeholder='댓글' onChange={(e)=>setContent(e.target.value)}></textarea>
        </div>
        <div className='text-center'>
          <button type='submit' className='btn btn-primary'>댓글작성</button>
        </div>

      </form>

      <ul className='list-group'>
        {
          comments.map((vo) => (
            <li key={vo.num}>
              <strong>{vo.uwriter}</strong>
              <span className='text-muted'>{vo.uredate}</span>
              <p>{vo.ucontent}</p>
            </li>
          ))
        }

      </ul>
    </div>
  )
}

export default UpBoardComm