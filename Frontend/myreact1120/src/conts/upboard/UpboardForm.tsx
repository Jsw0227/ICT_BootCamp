//UpboardForm.tsx
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import styles from './upboard.module.css';
import axios from 'axios';

//서버 측에서 처리할 파라미터는 생략이 가능한 ? 로 표기한다.
interface UpBoardVO {
    num?: number;
    title: string;
    writer: string;
    content: string;
    imgn?: string;
    hit?: number;
    reip?: string;
    bdate?: string;
    mfile: File | null; //클라이언트가 파일을 전송하기 위해서 선언 
}
const UpboardForm: React.FC = () => {
    //useState 선언 및 초기화 =? 만 빼고 초기화
    const [formData, setFormData] = useState<UpBoardVO>({
        title: '',
        writer: '',
        content: '',
        mfile: null as File | null
    });

    //File객체를 사용해서 읽어 들인 값을 미리보기 용으로 사용할 useState
    //ArrayBuffer 저장시 new FileReader에 의해서 바이너리로 저장 
    //사용할때는 string으로 변환 해준다.
    const [preview, setPreview] = useState<string | ArrayBuffer | null>(null);



    //form데이터를 change받아서 useState에 저장할 함수 
    const formChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        //선택된 target 즉, HTMLElement에서 name,value를 각각 받아 온다.
        const { name, value } = e.target
        // 받아온 데이터를 useState의 formData 객체에 각각 저장한다.
        setFormData({ ...formData, [name]: value })
    }
    //파일데이터를 change받아서 useState에 저장할 함수 
    const fileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        if (e.target.files) { //파일 업로드가 존재했을 때 if문을 실행한다.
            //useState에 파일 형태로 저장하겠다.
            //.files[0] 파일은 배열 형태로 저장되어서 넘어 온다.
            //setFormData({...formData,mfile:e.target.files[0]})
            // 미리보기 구현 **************************************
            //javascript FileReader()를 사용해서 읽어온다.
            //---------------------<file객체읽어 들임>------------------------
            const file = e.target.files[0]; // input type=file 
            //파일을 읽어 들이기 위한 FileReader() 객체 생성
            const reader = new FileReader();
            //Reader를 통해서 파일을 읽어 오기위해서 감지하는 영역 
            //여기서 파일을 읽어와서 핸들링하는 영역
            reader.onloadend = () => {  // 파일 Stream이 읽어 오는 영역  
                console.log("파일 이미지가 감지 됨!")
                console.log(reader.result);
                setPreview(reader.result);
            }
            //읽어올 파일의 주소를 FileReader에게 등록하자.
            reader.readAsDataURL(file);
            setFormData({ ...formData, mfile: file });
        }
    
}

//axios 사용방법
// const fN = async () => {
// await axios.post(url,data,{header})
// const response = await axios.get(url); response.data 
//}
// 입력후 리스트로 이동 하기 위한 훅을 생성
const navigate = useNavigate();

// change이후 입력값을 axios를 사용해서 전송
const myFormSubmit = async (e: React.FormEvent) => {
    e.preventDefault();// 폼 전송을 막기 
    //자바스크립트 데이터전송 객체 FormData()를 생성해서 Ajax로 전송
    //파라미터이름은 스프링부트일때 DTO(VO)의 property이다.
    //data.append('파라미터이름',값)
    //->upboardAdd(@ModelAttribute UpBoardVO vo)
    const data = new FormData();
    //useState에 저장된 값을 formData에 저장
    // data.append('파라미터이름',값);
    data.append('title', formData.title);
    data.append('writer', formData.writer);
    data.append('content', formData.content);
    if (formData.mfile) {
        data.append('mfile', formData.mfile);
        //-여기까지 useState에 저장된 값을 찾아와서 다시 FormData에 모든 값을 저장
        try {
            //파일 업로드 시 폼의 속성 예 => encType='multipart/form-data'
            //무조건 post방식이다. *****
            //axios.post(url,data,[{header}])
            //postman에서 테스트한 주소를 복사해서 붙여 넣기 <---
            const url = 'http://192.168.0.195/myictstudy/upboard/upboardAdd';
            await axios.post(url, data, {
                headers: { 'Content-Type': 'multipart/form-data' }
            });
            //페이지를 이동
            //navigate('/community/uplist') // 오류가 없으면 이동
            navigate('/community/uplist',{ preventScrollReset: true })
        } catch (error) {
            console.log(`Erro =>${error}`);  //오류 발생시 
        }
        console.log(`FormData 전송 시 name이 필수!  Title => ${formData.title}, 
           Writer =>${formData.writer}`);
    }
}
return (
    <div className={styles.container}>
        <h2 className={styles.title}>이미지 등록 예제</h2>
        <form onSubmit={myFormSubmit} className={styles.form}>
            <table className={styles.boardTable}>
                <tbody>
                    <tr>
                        <th>제목</th>
                        <td>
                            <input type="text" name="title" id="title" className={styles.input}
                                onChange={formChange} required
                            />
                        </td>
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td>
                            <input type="text" name="writer" id="writer"
                                className={styles.input}
                                onChange={formChange} required
                            /></td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td>
                            <textarea name="content" id="content"
                                style={{ width: '90%', height: '150px', padding: '8px' }}
                                onChange={formChange} required
                            />
                        </td>
                    </tr>
                    <tr>
                        <th>이미지</th>
                        <td><input type="file" name="mfile" id="mfile"
                            className={styles.input}
                            onChange={fileChange} required
                        /></td>
                    </tr>

                { preview &&
                (<tr>
                  {/* img src=이지경로 :이미지의 바이너리 자체를 
                  //src에 string 선언해서 이미가 읽어 들이도록 구현  */}
                   <td colSpan={2} style={{textAlign:'center'}}>
                     <img src={preview as string} alt='' 
                     style={{
                      width:'150px' , height:'150px', 
                      marginRight:'10px', marginBottom:'10px'
                    }} />
                     {/* {preview as string}  */}
                    </td>
                </tr>)
                }


                </tbody>
                <tfoot>
                    <tr>
                        <th colSpan={2}>
                            <button type="submit" className={styles.button}>등록하기</button>
                        </th>
                    </tr>

                </tfoot>
            </table>
        </form>
    </div>
)
}

export default UpboardForm