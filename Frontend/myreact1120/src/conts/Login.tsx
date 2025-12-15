import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../comp/AuthProvider';
//conts.Login.tsx
const Login: React.FC = () => {

  const [formData,setFormData] = useState({userid:'',password:''});
  const [message,setMessage] = useState('');
  const navigate = useNavigate();
  const {login} = useAuth();
  const inputChange = (e:React.ChangeEvent<HTMLInputElement>)=>{
    setFormData({...formData,[e.target.name]:e.target.value})
  };
  const submitLogin = async () => {
    const result = await login(formData.userid,formData.password);
    if(result ==='success'){
      setMessage('로그인 성공');
      navigate('/');
    }else if(result==='fail'){
      setMessage('아이디 또는 비밀번호가 틀렸습니다.');
    }else{
      setMessage('서버 오류');
    }
  }

  return (
    <div>
      <h2>로그인</h2>
      <input name='userid' onChange={inputChange} placeholder='아이디'/>
      <input name='password' type='password' onChange={inputChange} placeholder='비밀번호'/>
      <button onClick={submitLogin}>로그인</button>
      <p>{message}</p>
    </div>
  )
}

export default Login