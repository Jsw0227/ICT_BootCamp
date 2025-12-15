import React from 'react'
import { useLogin } from './LoginContext'
import Child from './Child';

const Parent: React.FC = () => {
    const {isLoggedIn,toggleLogin} = useLogin();
  return (
    <div style={{border:'1px solid gray', padding:10,marginTop:10}}>
      <h2>부모 컴포넌트</h2>
      <p>로그인 상태 : {isLoggedIn ? '로그인 됨' : '로그아웃됨' }</p>
      <button onClick={toggleLogin}> 부모상태토글</button>
      <Child/>
    </div>
  )
}

export default Parent