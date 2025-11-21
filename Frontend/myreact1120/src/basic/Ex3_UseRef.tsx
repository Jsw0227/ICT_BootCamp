import React, { useRef, useState } from 'react';

const Ex3_UseRef: React.FC = () => {
    //중요개념) 컴포넌트가 다시 렌더링될 때마다 renderCountRef.current 값을 증가 하지만
    //리렌더링 없이 값 저장 (renderCountRef) 
    //useState와 달리 useRef는 값이 바뀌어도 리렌더링을 발생시키지 않는다.
    //화면에 보이는 값은 계속 업데이트되지만, 컴포넌트는 불필요하게 다시 그려지지 않음
    const inputRef = useRef<HTMLInputElement>(null);
    const [value, setValue] = useState('');
    const renderCountRef = useRef(0);
    renderCountRef.current += 1;
    const spanRef = useRef<HTMLSpanElement>(null);
    //useRef를 사용해서 DOM 직접 수정 하는 함수 
    const showValue = () => {
        if (inputRef.current && spanRef.current) {
            spanRef.current.textContent = `입력값(useRef): ${inputRef.current.value}`;
        }
    };
    const focusInput = () => {
        inputRef.current?.focus();
    };
    const getValue = () => {
        if (inputRef.current) {
            alert(`입력값 ${inputRef.current.value}`);
        }
    };

    return (
        <div>
            <p>렌더링 횟수: {renderCountRef.current}</p>
            <input
                type="text"
                ref={inputRef}
                value={value}
                onChange={(e) => setValue(e.target.value)}
            />
            <button onClick={focusInput}>Focus</button>
            <button onClick={getValue}>값 확인</button>
            <button onClick={showValue}>Span(useRef)에 출력</button>
            <p>입력값(useState): {value}</p>
            <span ref={spanRef}></span>
        </div>
    );
};

export default Ex3_UseRef;
