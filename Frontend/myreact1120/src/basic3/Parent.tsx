import React from 'react';
import Child from './Child';
import { useLogin } from './LoginContext';
const Parent: React.FC = () => {
     const { isLoggedIn, toggleLogin } = useLogin();
  return (
    <div style={{ border: '1px solid blue', padding: 10 }}>
      <h2>부모 컴포넌트</h2>
       <p>부모 로그인 상태: {isLoggedIn ? '로그인됨' : '로그아웃됨'}</p>
       <button onClick={toggleLogin}>부모상태 토글</button>
       <Child/>
    </div>
  );
};
export default Parent;