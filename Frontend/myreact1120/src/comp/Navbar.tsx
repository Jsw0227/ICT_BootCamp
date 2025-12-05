//comp/Navbar.tsx
// Link : 단순하게 다른 경로로 이동하는 역할만 가능
// NavLink : 현재 경로와 일치 할때 활성화 상태(스타일/클래스등)를 프로그램에 의해서 적용할 수 있다.
// isActive 값을 받아서 현재 url과 to 속성으로 준 경로값이 일치하면 자동으로 active상태가 됨 
import React from 'react'
import { Link } from 'react-router-dom'
import style from './navbar.module.css'
import { NavLink } from 'react-router-dom'
import DropdownNav from './DropdownNav'

const Navbar: React.FC = () => {
    //style클래스를 정의하기 : 스타일로 활성화 상태를 구분하기 위한
    //함수 
    const commonLinkClass = ({ isActive }: { isActive: boolean }) => {
        return isActive ? `${style.link} ${style.active}` : style.link;
    }
    return (
        <nav className={style.navbar}>
            <NavLink to="/" className={commonLinkClass}>Home</NavLink>
            <NavLink to="/board" className={commonLinkClass}>게시판</NavLink>
            <NavLink to="/gallery" className={commonLinkClass}>갤러리</NavLink>
            <NavLink to="/chart" className={commonLinkClass}>차트</NavLink>
            {/* <NavLink to="/community" className={commonLinkClass}>커뮤니티</NavLink> */}
            <DropdownNav/>
            <NavLink to="/diary" className={commonLinkClass}>Diary</NavLink>
        </nav>
                // <nav style={{ marginTop: '10px' }}>
        //     <Link to="/" style={{ marginRight: '10px' }}>Home</Link>
        //     <Link to="/board" style={{ marginRight: '10px' }}>게시판</Link>
        //     <Link to="/gallery" style={{ marginRight: '10px' }}>갤러리</Link>
        //     <Link to="/chart" style={{ marginRight: '10px' }}>차트</Link>
        //     <Link to="/community" style={{ marginRight: '10px' }}>커뮤니티</Link>
        //     <Link to="/diary">일기장</Link>
        // </nav>
    )
}

export default Navbar