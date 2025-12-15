import { BrowserRouter as Router } from 'react-router-dom';
import Layout from './comp/Layout';
import AppRoutes from './router/AppRoutes';
import { useState } from 'react';
// import { LoginProvider } from './basic3/LoginContext';
// import LoginTest from './basic4_login/LoginTest';
// import { LoginProvider } from './basic4_login/LoginContext';
import { AuthProvider } from './comp/AuthProvider';
// import Parent from './basic3/Parent';
// import Parent from './basic2/Parent';

function App() {
  // 1.property 실습
  // const [isLoggedIn, setIsLoggedIn] = useState(false);
  // const toggleLogin = () => setIsLoggedIn(prev => !prev);


  return (
    // <AuthProvider>
    //   <div style={{ border: '1px solid red', padding: 10 }}>
    //   </div>
    // </AuthProvider>
    <AuthProvider>
      <Router>
        <Layout>
          <AppRoutes />
        </Layout>
      </Router>
    </AuthProvider>
  );
}
export default App;
