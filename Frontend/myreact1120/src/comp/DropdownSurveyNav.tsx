//comp/DropdownNav.tsx
import React, { useRef, useState } from 'react'
import style from './navbar.module.css'
import { NavLink } from 'react-router-dom';

const DropdownSurveyNav: React.FC = () => {
 //true, false에 따라서 드랍다운 여부를 결정하기 위한 상태값 
 const [isOpen,setIsOpen] = useState(false);
 // dom 요소에 접근할 useRef
 const dropdownRef = useRef<HTMLDivElement>(null); 
 //toggleDropdown 클릭이 될 때 useState 값에 대한 toggle처리를 한다.
 const toggleDropdown =() => { setIsOpen((prev) => !prev) }
 const closeDropdown = () => { setIsOpen(false)}
  //active 스타일을 함수적으로 제어
  const linkClass = ({ isActive } : {isActive : boolean}) => 
    isActive ? `${style.link} ${style.active}` : style.link
  return (
    <div ref={dropdownRef} className={style.dropdown}>
        <div onClick={toggleDropdown} className={style.link}>
             설문조사 <span className={style.arrow}>{isOpen? '▲' : '▼'}</span>
        </div>
        {isOpen && (<div className={style.dropdownContent}>
            <NavLink to="/surveyForm" onClick={closeDropdown} className={linkClass}>
                설문폼
            </NavLink>
            <NavLink to="/surveyClient" onClick={closeDropdown} className={linkClass}>
                surveyClient
            </NavLink>
            <NavLink to="/surveyResult" onClick={closeDropdown} className={linkClass}>
               surveyResult
            </NavLink>
            
        </div>)}
    </div>
  )
}
export default DropdownSurveyNav