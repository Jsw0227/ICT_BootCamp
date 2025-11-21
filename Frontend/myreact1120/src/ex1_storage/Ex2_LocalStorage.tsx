import React, { useEffect, useState } from 'react'
//Ex2_LocalStorage.tsx
const Ex2_LocalStorage: React.FC = () => {
    const [msg, setMsg] = useState<string | null>('');
    useEffect(() => {
        setMsg(localStorage.getItem("msg"));
    }, [])
    return (
        <div>
            <p style={{ backgroundColor: "orange", color: "white" }}>
                {msg}
            </p>
        </div>
    )
}
export default Ex2_LocalStorage