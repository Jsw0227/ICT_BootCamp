import axios from 'axios';
import React, { useEffect, useState } from 'react'
//192.168.0.31/myictstudy0521/upboard/upcommList?num=18
//json의 결과를 보고 인터페이스를 정의 해라
/*
[
    {
        "num": 21,
        "ucode": 18,
        "uwriter": "테스형",
        "ucontent": "json테스트",
        "reip": "192.168.0.26",
        "uregdate": "2025-05-29 15:33:01"
    }
]
*/
//부모로 부터 전송되어 오는 properties
interface UpBoardCommProps {
   num?: string;
}
//서버로 부터 전송되어 오는 json구조를 인터페이스로 선언 
interface UpBoardCommVO {
  num: number;
  ucode: number;
  uwriter: string;
  ucontent: string;
  reip: string;
  uregdate: string;  
}
 //step 6) 댓글 같은 경우는 입력한 것이 즉시 반영 되야 한다.
 // 서버측 함수를 복붙 
 //http://192.168.0.31/myictstudy0521/upboard/upcommList?num=1
	//@GetMapping("/upcommList")
	//public List<UpBoardCommVO> listBoardComm(@RequestParam("num") int num){
  //interface 정의 하기 
const UpBoardComm: React.FC<UpBoardCommProps> = ({num}) => {
  const [comments, setcomments] = useState<UpBoardCommVO[]>([]);
  const getComments = async () => {
    try {
      const url = `http://192.168.0.195/myictstudy/upboard/upcommList?num=${num}`;
      const response = await axios.get<UpBoardCommVO[]>(url);
      console.log("==============================")
      console.log(response.data); 
      setcomments(response.data);
    } catch (error) {
       console.error("데이터 로딩 실패!", error);
    }
  }
  //상세보기의 키값인 num값이 변경이 될 때 마다 getComments()를 호출해서 새로운 데이터를 초기화 한다.
  useEffect(() => {
  console.log("Num => "+num);
  getComments();
   }, [num]);
  //step 3) 폼에서 입력한 값을 onChage 이벤트가 발생할 때 마다 값을 저장하기 위한 저장장소를 선언한다.
  // useState를 정의함. 
  const [writer,setWriter] = useState("");
  const [content,setContent] = useState("");
  const commnentSubmit = async (e:React.FormEvent) => {
    e.preventDefault();
    //alert("Form 동작!");
    // useState에 입력된 글을 출력 
    console.log(`Writer => ${writer}`);
    console.log(`Content => ${content}`); 
    //서버측에 데이터를 받는 유형을 확인한 후 지금처럼 @RequestBody 형태 즉, 
    // json이면 front에서는 json으로 데이터를 전송해야 함. *****
    // backend의 함수 첫줄만 복붙해보기 
    //@PostMapping("/upcommAdd")
	//public ResponseEntity<?> upBoardComm(@RequestBody UpBoardCommVO vo){
    // useState에서 받아온 데이터를 json화 시키기
    // 이때 키 : 값  일때 키는 UpBoardCommVO 의 property와 같아야 한다.
    const commentData = {
       ucode : num,
       uwriter : writer,
       ucontent: content,
       reip:'192.168.0.26'
    }    
     //step 5) axios.post(url,data,{headers:'Content-Type':'application/json'}) 를 사용해서 서버측으로 json데이터를 전송한다. 
     try {
        await axios.post(`http://192.168.0.195/myictstudy/upboard/upcommAdd`,commentData,
           { headers:{'Content-Type':'application/json'} }
        )
      //입력후 초기화 및 댓글 리스트 다시 실행
      setWriter("");
      setContent("");
      getComments();
     } catch (error) {
        console.error("전송 실패!", error);
     }
  }
  return (
    <div className='mt-4'>
      <h4>Comments</h4>
      <form className='mb-3' onSubmit={commnentSubmit}>
        <div className="mb-2">
        <input type='text' placeholder='작성자' className='form-control'
              onChange={(e) => setWriter(e.target.value)}
            />
        </div>
        <div className="mb-2">
             <textarea className='form-control' placeholder='댓글'
              onChange={(e) => setContent(e.target.value)}
            ></textarea>
        </div>
        <div className='text-center'>
            <button type="submit" 
            className="btn btn-primary">댓글작성</button>

        </div>
      </form>
    {/* step final  댓글 리스트  */}
         <ul className="list-group">
            {
                comments.map((vo)=>(
                <li key={vo.num} className="list-group-item">
                    <strong>{vo.uwriter}</strong>
                    <span className="text-muted">{vo.uregdate}</span>
                    <p>{vo.ucontent}</p>
                </li>))
            }
         </ul>
    </div>
  )
}

export default UpBoardComm