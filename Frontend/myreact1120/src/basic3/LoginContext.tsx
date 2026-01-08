import { createContext, useContext, useState } from "react";

/*
useContext는 createContext()로 만든 전역 데이터를 하위 컴포넌트 어디에서든 꺼내 쓸 수 
있도록 해주는 훅.
즉, 
AuthContext.Provider가 제공하는 값 (member, login, logout 등)을 받아오는 
역할을 하는 것이다.
*/

//basic3/LoginContext.tsx
interface LoginContextType {
  isLoggedIn: boolean;
  toggleLogin: () => void;
}
// 1단계: Context 생성
// const AuthContext = createContext<AuthContextProps | undefined>(undefined);
const LoginContext = createContext<LoginContextType | undefined>(undefined);
export const LoginProvider: React.FC<{ children: React.ReactNode }> = 
    ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const toggleLogin = () => setIsLoggedIn(prev => !prev);
//   2단계: Context에 값 
    return (
    <LoginContext.Provider value={{ isLoggedIn, toggleLogin }}>
      {children}
    </LoginContext.Provider>
  );
}
//3단계: useAuth() 에서 값을 사용할 수 있도록 제공
export const useLogin = () => {
    //커스텀 훅으로 useContext를 정의 
  const context = useContext(LoginContext);
  if (!context) throw new Error("useLogin은 LoginProvider 안에서만 사용해야 합니다.");
  return context;
};