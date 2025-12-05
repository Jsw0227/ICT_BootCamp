import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import styles from './upboard.module.css';
import axios from 'axios';

// 서버 측에서 처리할 파라미터는 생략이 가으한 ?(물음표)로 표기
interface UpBoardVO {
    num?: string;
    title: string;
    writer: string;
    content: string;
    imgn?: string;
    hit?: number;
    reip?: string;
    mfile: File | null; // 클라이언트가 파일은 전송하기 위해 선언
}

const UpboardForm: React.FC = () => {

    // useState 선언 및 초기화 =? 만 빼고 초기화
    const [formData, setFormData] = useState<UpBoardVO>({
        title: '',
        writer: '',
        content: '',
        mfile: null as File | null
    });
    //File객체를 사용해서 읽어 들인 값을 미리보기 용으로 사용할 useState
    //ArrayBuffer 저장 시 new FileReader에 의해서 바이너리로 저장
    //사용 할 때는 

    const [preview, setPreview] = useState<string | ArrayBuffer | null>(null);


    // form 데이터를 change 받아서 useState에 저장할 변수
    const formChange = (e:React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const {name, value} = e.target              // 선택된 target 즉, HTMLElement에서 name, value를 각가 받아옴
        setFormData({...formData,[name]:value})     // 받아온 데이터를 useState의 객체에 각각 저장
    }

        // 파일 데이터를 change 받아서 useState에 저장할 변수
        const fileChange = (e:React.ChangeEvent<HTMLInputElement>) => {
            if(e.target.files){   // 파일 업로드가 존재할 때 if문 실행
                //setFormData({...formData, mfile:e.target.files[0]})  // useState에 파일 형태로 저장
              //---------------------file 객체 읽어들임>----------------------------
                const file =e.target.files[0];
              // 파일을 읽어 들이기 위한 FileReader() 객체 생성
              const reader = new FileReader();
              //Reader를 통해서 파일을 읽어 오기 위해서 감지하는 영역
              //여기서 파일을 읽어와서 핸들링 하는 영역
              reader.onloadend=()=>{ // 파일 Stream이 읽어오는영역
                console.log("파일 이미지가 감지 됨!")
                console.log(reader.result);
                setPreview(reader.result);
              }
              //읽어올 파일의 주소를 FileReader에게 등록하자
              reader.readAsDataURL(file);
              setFormData({...formData,mfile:file});
            }
        }

        const navigate = useNavigate();  // 입력 후 리스트로 이동하기 위한 훅
        const myFormSubmit = async(e:React.FormEvent) => {    // change 이후 입력값을 axios를 사용해 전송
            e.preventDefault();  // 폼 전송 막기
            
            const data = new FormData();    // 저장된 값을 formData에 저장-> data.append('파라미터 이름', 값);
            data.append('title', formData.title);
            data.append('writer', formData.writer);
            data.append('content', formData.content);
            if (formData.mfile) {
                data.append('mfile', formData.mfile);

            try {
                // axios/post(url, data, [{header}]) -> ** post 방식 ** 
                const url = 'http://192.168.0.13/myictstudy/upboard/upboardAdd';
                await axios.post(url, data, {
                    headers:{'Content-Type': 'multipart/form-data'}

                });
                // 페이지 이동
                navigate('/community/uplist') // 오류 없을 시
                } catch (error) {
                    console.log(`Error => ${error}`) // 오류 발생 시
                }
                console.log(`FormData 전송 시 name이 필수!! Title => ${formData.title}, Writer => ${formData.writer}`)
            }
        }
  return (
    <div>
      <div className={styles.container}>
        <h2 className={styles.title}>이미지 등록 예제</h2>
        <form onSubmit={myFormSubmit} className={styles.form}>
        <table>
            <tbody>
                <tr>
                    <th>제목</th>
                    <td>
                        <input type="text" name="title" id="title" className={styles.input}
                            onChange={formChange} required/>    
                    </td>                  
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>
                        <input type="text" name="writer" id="writer" className={styles.input}
                            onChange={formChange} required/>    
                    </td>                  
                </tr>
                <tr>
                    <th>내용</th>
                    <td>
                        <textarea name="content" id="content"
                            style={{width: '90%', height:'150px', padding: '8px'}} 
                            onChange={formChange} required/>
                    </td>                  
                </tr>
                <tr>
                    <th>이미지</th>
                    <td>
                        <input type="file" name='mfile' id='mfile' 
                            className={styles.input}               
                            onChange={fileChange} required/>
                    </td>   
                </tr>
                {preview && 
                (<tr>
                  {/* img src= 이지경로 : 이미지의 바이너리 자체를 src에 string 선언해서 이미지가읽어 들이도록 구현 */}
                  <td colSpan={2} style={{textAlign:'center'}}>
                    <img src={preview as string} alt="" style={{width:'150px' , height:'150px', marginRight:'10px', marginBottom:'10px'}} />
                    </td>

                </tr>

                )
                }
            </tbody>

            <tfoot>
                <tr>
                    <td colSpan={2}>
                        <button type='submit' className={styles.button} >등록하기</button>
                    </td>
                </tr>
            </tfoot>
        </table>
        </form>
      </div>
    </div>
  )
}

export default UpboardForm