//basic3/Child.tsx
import React from 'react'
import { useLogin } from './LoginContext';
//useContext 방식 : 전역 상태 접근 (Child가 직접 사용)
//컴포넌트 구조와 상태 분리 가능
const Child: React.FC = () => {
  const { isLoggedIn, toggleLogin } = useLogin();//커스텀 훅
  return (
   <div style={{ border: '1px solid gray', padding: 10, marginTop: 10 }}>
      <h3>자식 컴포넌트</h3>
      <p>로그인 상태: {isLoggedIn ? '로그인됨' : '로그아웃됨'}</p>
      <button onClick={toggleLogin}>상태 토글</button>
    </div>
  )
}

export default Child