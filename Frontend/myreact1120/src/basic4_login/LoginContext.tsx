import React, { createContext, useContext, useState } from "react";

interface Member {
    userid: string;
    name: string;
    email: string;
}

interface LoginContextType {
    member: Member | null;
    login: (userid: string, password: string) => Promise<'success' | 'fail' | 'error'>;
    logout: () => void;
    updateMemberName: (name: string) => void;
    isLoggedIn: boolean;
}

const LoginContextType = createContext<LoginContextType | undefined>(undefined);

export const LoginProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
    const [member, setMember] = useState<Member | null>(null);
    const login = async (userid: string, password: string): Promise<'success' | 'fail' | 'error'> => {
        try {
            if (userid === 'admin' && password === '1234') {
                const user: Member = {
                    userid,
                    name: '관리자',
                    email: 'admin@example.com'
                };
                setMember(user);
                return 'success';
            }else{
                return 'fail';
            }

        } catch (e) {
            console.error(e);
            return 'error';
        }
    };

    const logout = () => {
        setMember(null);
    };

    const updateMemberName = (name:string)=>{
        setMember(prev =>(prev?{...prev,name}:prev));
    };

    const isLoggedIn = member !== null;
    return(
        <LoginContextType.Provider value={{member,login,logout,updateMemberName,isLoggedIn}}>
            {children}
        </LoginContextType.Provider>
    )
}

export const useLogin = () => {
    const context = useContext(LoginContextType);
    if(!context) throw new Error('useLogin은 LoginProvider 안에서 사용되어야 합니다.')
    return context;
}