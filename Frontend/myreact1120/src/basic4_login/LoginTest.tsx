import React from 'react'
import { useLogin } from './LoginContext'

const LoginTest: React.FC = () => {
  const {member,login,logout,updateMemberName,isLoggedIn} = useLogin();
  const loginProcess = async () => {
    const result = await login('admin','1234');
    if(result === 'success') alert('로그인 성공');
    else alert('로그인 실패');
  };
  
    return (
    <div style={{padding:10,border:'1px solid gray'}}>
      <h2>LoginTest</h2>
      {isLoggedIn ? (
        <>
        <p>환영합니다, {member?.name} ({member?.email})</p> 
        <button onClick={logout}>로그아웃</button>
        <button onClick={()=>updateMemberName('테스형')}>이름 변경</button>
        </>)
        : (<button onClick={loginProcess}>로그인</button>)
        
        }
    </div>
  )
}

export default LoginTest