import React from 'react'
import { Route, Routes } from 'react-router-dom'
import Home from '../conts/Home'
import BoardList from '../conts/board/BoardList'
import BoardForm from '../conts/board/BoardForm'
import BoardDetail from '../conts/board/BoardDetail'
import Gallery from '../conts/gallery/Gallery'
import GalleryForm from '../conts/gallery/GalleryForm'
import GalleryDetail from '../conts/gallery/GalleryDetail'
import Chart from '../conts/Chart'
import Community from '../conts/Community'
import Diary from '../conts/Diary'
import Login from '../conts/Login'
import Signups from '../conts/member/Signups'
import UpboardList from '../conts/upboard/UpboardList'
import UpboardForm from '../conts/upboard/UpboardForm'

const AppRoutes: React.FC = () => {
    const routeList = [
        { path: '/', element: <Home /> },
        { path: '/board', element: <BoardList /> },        // 게시판 목록
        { path: '/board/write', element: <BoardForm /> },   // 게시판 글쓰기
        { path: '/board/:id', element: <BoardDetail /> },  // 게시판 상세페이지 
        { path: '/gallery', element: <Gallery /> },
        { path: '/gallery/write', element: <GalleryForm /> },
        { path: '/gallery/:id', element: <GalleryDetail /> },
        { path: '/chart', element: <Chart /> },
        { path: '/community', element: <Community /> },
        { path: '/community/uplist', element:<UpboardList/>},//upboard
        { path: '/community/upform', element:<UpboardForm/>},///community/upform
        { path: '/diary', element: <Diary /> },
        { path: '/login', element: <Login /> },
        { path: '/signup', element: <Signups /> },
    ]
    return (
        <Routes>
            {
                routeList.map((route, idx) => (
                    <Route key={idx}{...route} />
                ))
            }
        </Routes>
    )
}

export default AppRoutes