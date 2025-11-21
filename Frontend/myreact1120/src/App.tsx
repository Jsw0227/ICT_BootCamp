import React from 'react';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import Layout from './comp/Layout';
import Home from './conts/Home';
import Signups from './conts/member/Signups';
import Login from './conts/Login';
import BoardList from './conts/board/BoardList';

import Chart from './conts/Chart';
import Diary from './conts/Diary';
import Community from './conts/Community';
import FilterTest from './FilterTest';
import BoardDetail from './conts/board/BoardDetail';
import BoardForm from './conts/board/BoardForm';
import Gallery from './conts/gallery/Gallery';
import GalleryDetail from './conts/gallery/GalleryDetail';
import GalleryForm from './conts/gallery/GalleryForm';
import AppRoutes from './router/AppRoutes';
import Ex1_LocalStorage from './ex1_storage/Ex1_LocalStorage';
import Ex2_LocalStorage from './ex1_storage/Ex2_LocalStorage';
import Ex1_UseCallback from './basic/Ex1_UseCallback';
import Ex2_PersonUseCallBack from './basic/Ex2_PersonUseCallBack';
import Ex3_UseRef from './basic/Ex3_UseRef';
function App() {
  return (
    // <div>
    //   <FilterTest/>
    // </div>
    // <Router>
    //   <Layout>
    //     <Routes>
    //       <Route path='/' element={<Home />} />
    //       <Route path='/board' element={<BoardList />} />
    //        {/* http://localhost:3000/board/1 , 2 모두가 :id로 들어감 */}
    //       <Route path='/board/:id' element={<BoardDetail/>} />
    //       <Route path='/board/write' element={<BoardForm/>}/>
    //       <Route path='/gallery' element={<Gallery />} />
    //       <Route path='/gallery/:id' element={<GalleryDetail />} />
    //       <Route path='/gallery/write' element={<GalleryForm />} />
    //       <Route path="/signup" element={<Signups />} />
    //       <Route path="/chart" element={<Chart />} />
    //       <Route path="/diary" element={<Diary />} />
    //       <Route path="/login" element={<Login />} />
    //       <Route path='/community' element={<Community/>}/>
    //     </Routes>
    //   </Layout>
    // </Router>

    <Router>
      <Layout>
        <AppRoutes />
      </Layout>
    </Router>
    
    
    // <>
    // <Ex1_LocalStorage/>
    // <Ex2_LocalStorage/>
    // </>
    // <Ex1_UseCallback/>
    // <Ex2_PersonUseCallBack/>
    // <Ex3_UseRef/>
  
  );
}
export default App;
