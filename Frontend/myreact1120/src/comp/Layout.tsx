import React from 'react'
import Navbar from './Navbar';
import { useAuth } from './AuthProvider';
import { useNavigate } from 'react-router-dom';
import { NavLink } from 'react-router-dom';
interface LayoutProps {
    children: React.ReactNode;
}
const Layout: React.FC<LayoutProps> = ({children}) => {

  const {isLoggedIn,  member, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = async () => {
    await logout();
    alert('로그아웃 되었습니다.');
    navigate('/');
  };


  return (
    <div style={{ maxWidth: '1200px', margin: '0 auto', padding: '20px' }}>
      <header style={{ 
        display: 'flex', 
        justifyContent: 'space-between', // 공간을 사이로 벌려놓기
        alignItems: 'center', //중앙 정렬
        backgroundColor: '#4caf50', 
        color: 'white', 
        padding: '10px 20px', 
        borderRadius: '8px' 
        }}>
        <h1>ICT Password</h1>
        {/* <div>
            <a href="/login" style={{ marginRight: '10px', color: 'white' }}>로그인</a>
            <a href="/signup" style={{ color: 'white' }}>회원가입</a>
        </div> */}
         <div>
          {isLoggedIn  ? (
            <>
              <span style={{ marginRight: '10px' }}>{member?.name}님 환영합니다</span>
              {/* 마이페이지 링크 추가 */}
              <NavLink
                to="/mypage"
                style={{ marginRight: '10px', color: 'white', textDecoration: 'underline' }}
              >
                마이페이지
              </NavLink>

              <button onClick={handleLogout} style={{ color: 'white', background: 'transparent'
                , border: 'none' }}>
                로그아웃
              </button>
            </>
          ) : (
            <>
              <a href="/login" style={{ marginRight: '10px', color: 'white' }}>로그인</a>
              <a href="/signup" style={{ color: 'white' }}>회원가입</a>
            </>
          )}
        </div>
      </header>
      <Navbar/>
      <main>{children}</main>
      <footer style={{
            backgroundColor: '#4caf50', 
            color: 'white', 
            padding: '10px', 
            borderRadius: '0 0 8px 8px',
            textAlign:'center'
        }}>
      @ 2025 IctPassword. 
      </footer>
    </div>
  )
}

export default Layout