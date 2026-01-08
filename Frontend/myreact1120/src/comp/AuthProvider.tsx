//comp/AuthProvider.tsx
import React, { createContext, useContext, useEffect, useState } from 'react';
import axios from 'axios';
interface Member {
  userid: string;
  name: string;
  email: string;
}
interface AuthContextProps {
  member: Member | null;
  checkLogin: () => void;
  isLoggedIn: boolean;
  login: (userid: string, password: string) => Promise<'success' | 'fail' | 'error'>;
  logout: () => void;
  updateMemberName: (name: string) => void;
  updateMemberEmail: (email: string) => void;
  // ######################################################################### 2025-12-17 추가 
  loading: boolean;  // 로딩을 추가해서 checkLogin()이 끝날 때까지 기다리기 위한 기능
}
// 1단계: Context 생성
const AuthContext = createContext<AuthContextProps | undefined>(undefined);
export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [member, setMember] = useState<Member | null>(null);

  // ######################################################################### 2025-12-17 추가
  const [loading, setLoading] = useState(true);  // loadin


  //로그인 상태를 체크 해주는 함수
  const checkLogin = async () => {
    try {
      // withCredentials: true
      // backend에서도 .allowCredentials(true) 를 설정해야 함.(세션을 사용해서 동기화 할때 사용 )
      //${process.env.REACT_APP_BACK_END_URL}
      const res = await axios.get(`${process.env.REACT_APP_BACK_END_URL}/api/login/session`, {
        withCredentials: true
      });
      if (res.data?.userid) {
        setMember(res.data); // 로그인 된 정보를 받아서 useState에 저장한다.
      }else {
        setMember(null); //로그인 상태가 아니라면 useState를 초기화 
      } 
    } catch {
      setMember(null); // 문제가 발생해도 초기화 
    } finally{
    // ######################################################################### 2025-12-17 추가
       //finally는 무조건 실행되니깐 
      //여기서  checkLogin() 함수의 성공 여부와 관계없이 로그인 확인 작업이 끝났음을 알리기 위해서 false로 설정 함
      // 어쨌든 로그인 정보를 받아오던지, 안받아 오던지 여기는 무조건 실행이 끝났다고 초기화 하는 목적이다.
      //RequireAuth.tsx 에사 loading = true인 동안은 화면을 안 바꾸고 대기 하고 있다가 
      //loading = false가 된 후 member가 null인지 아닌지를 판단해서 라우팅 처리를 해라라고 설정 될꺼다.
      setLoading(false);
    }
  };

  const login = async (userid: string, password: string): Promise<'success' | 'fail' | 'error'> => {
    try {
      const res = await axios.post(
        `${process.env.REACT_APP_BACK_END_URL}/api/login/dologin`,
        { userid, password },{ withCredentials: true }
      );
      if (res.data === 'success') {
        await checkLogin(); // 로그인 성공 후 세션 정보 불러오기
        return 'success';
      } else {
        return 'fail';
      }
    } catch {
      return 'error';
    }
  };

  const logout = async () => {
    await axios.get(`${process.env.REACT_APP_BACK_END_URL}/api/login/dologout`, {
      withCredentials: true
    });
    setMember(null);
  };
  //새로 고침을 해도 초기화로 checkLogin() 을 호출해서 유지 시킨다.
   useEffect(() => {
   
   // ######################################################################### 2025-12-17 추가
    if (window.location.pathname !== '/login') {
      checkLogin();
    }else {
      setLoading(false); 
    }

  }, []);
  const updateMemberName = (name: string) => {
    setMember(prev => (prev ? { ...prev, name } : prev));
  };
  const updateMemberEmail = (email: string) => {
    setMember(prev => (prev ? { ...prev, email } : prev));
  };
  const isLoggedIn = member !== null;
// 2단계: Context에 값 제공
  return (
   // ######################################################################### 2025-12-17 추가
    <AuthContext.Provider value={{   member,
        isLoggedIn,
        checkLogin,
        login,
        logout,
        updateMemberName,
        updateMemberEmail,
        loading
        }}>
      {children}
    </AuthContext.Provider>
  );
};
 // 3단계: useAuth() 에서 값을 사용할 수 있도록 제공공
export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) throw new Error('AuthContext 은 AuthProvider  안에서만 사용해야 합니다.');
  return context;
};
// 1단계: Context 생성
// const AuthContext = createContext<AuthContextProps | undefined>(undefined);

// // 2단계: Context에 값 제공
// <AuthContext.Provider value={{ member, setMember, login, logout, checkLogin }}>
//   {children}
// </AuthContext.Provider>

// // 3단계: useAuth() 에서 값을 사용할 수 있도록 제공공
// const context = useContext(AuthContext);
/*
useContext는 createContext()로 만든 전역 데이터를 하위 컴포넌트 어디에서든 꺼내 쓸 수 있도록 해주는 훅.
즉, AuthContext.Provider가 제공하는 값 (member, login, logout 등)을 받아오는 역할을 하는 것이다.
*/
