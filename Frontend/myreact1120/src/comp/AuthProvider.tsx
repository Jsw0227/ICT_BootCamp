import axios from "axios";
import { createContext, useContext, useEffect, useState } from "react";

interface Member{
    userid:string;
    name:string;
    email:string;
}

interface AuthContextProps{
    member:Member | null;
    checkLogin:()=>void;
    isLoggedIn: boolean;
    login:(userid: string, password:string)=> Promise<'success'|'fail'|'error'>;
    logout: ()=>void;
    updateMemberName: (name:string)=>void;
    updateEmailName: (email:string)=>void;
}

const AuthContext = createContext<AuthContextProps | undefined>(undefined);

export const AuthProvider: React.FC<{children:React.ReactNode}>= ({children}) => {
    const[member,setMember] = useState<Member | null>(null);
    const checkLogin = async () => {
        //http://192.168.0.14/myictstudy/api/login/session
        // withCredentials:true 서버와 session 통신
        try {
            const res = await axios.get('http://192.168.0.14/myictstudy/api/login/session',{
                withCredentials:true
            });
            if(res.data?.userid){
                setMember(res.data);
            }else{
                setMember(null);
            }
        } catch (error) {
            setMember(null);
        }
    }
    const login = async (userid:string, password:string): Promise<'success' | 'fail'|'error'> => {

        try {
            const res = await axios.post('http://192.168.0.14/myictstudy/api/login/dologin',{userid,password},{withCredentials:true})
            if(res.data == 'success'){
                //로그인 성공 했으니 상태정보를 불러오는 메서드 호출
                await checkLogin();
                return 'success';
            }else{
                return 'fail';
            }
        } catch (error) {
            return 'error';
        }
    }
    const logout = async () => {
        await axios.get('http://192.168.0.14/myictstudy/api/login/dologout',{withCredentials:true});
        setMember(null);
    }
    //렌더링 시 useEffect 를 사용해서 초기화
    useEffect(() => {checkLogin();},[]);

    const updateMemberName = (name: string) => {
        setMember(prev => (prev ? {...prev,name}:prev));
    }
    const updateEmailName = (email: string) => {
        setMember(prev => (prev ? {...prev,email}:prev));
    }
    const isLoggedIn = member !== null;
    
    return(
        <AuthContext.Provider value={{member, checkLogin,isLoggedIn,login,logout,updateMemberName,updateEmailName}}>
        {children}
        </AuthContext.Provider>
    )
}

export const useAuth = () => {
    const context = useContext(AuthContext);
    if(!context) throw new Error('AuthContext는 AuthProvider 안에서만 사용해야 합니다.');
    return context;
}