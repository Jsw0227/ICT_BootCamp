import React from 'react'
import { useLogin } from './LoginContext';

const Child: React.FC = () => {
    const {isLoggedIn,toggleLogin} = useLogin();
    return (
        <div style={{ border: '1px solid gray', padding: 10, marginTop: 10 }}>
            <h3>자식 컴포넌트</h3>
            <p>로그인 상태 : {isLoggedIn ? '로그인 됨' : '로그아웃됨'}</p>
            <button onClick={toggleLogin}> 상태토글</button>
        </div>
    )
}

export default Child