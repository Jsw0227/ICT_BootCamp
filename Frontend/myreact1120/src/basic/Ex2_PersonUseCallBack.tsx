import React, { useCallback, useMemo, useState } from 'react'
//sendSubmit과 changeData를 useCallback으로 감싸 최적화 시킨다.
//sendSubmit은 입력값과 nextId에 의존하므로 초기화 배열에 포함.
//changeData는 단순히 색상만 바꾸므로 초기화 배열은 [].
const Ex2_PersonUseCallBack: React.FC = () => {
    const [name, setName] = useState('');
    const [age, setAge] = useState('');
    const [ssn, setSsn] = useState('');
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [people, setPeople] = useState<any[]>([]);
    const [nextId, setNextId] = useState(1);
    const [chcolor, setChcolor] = useState(false);
    const [bgcolors, setBgcolors] = useState('orange');

    // useCallback으로 메모이제이션된 submit 감싸서 핸들링 한다.
    // useCallback(()=>{},[])
    // 감시하고 있는 값이 바뀔 때마다 새로운 함수가 생성이 되고 , 이전 값을 사용하지 않고 새로운 값을 반영하도록
    // 재 생성하도록 구현했음 
    const sendSubmit =
        useCallback(
            (e: React.FormEvent<HTMLFormElement>) => {
                e.preventDefault(); //폼의 전송을 막음
                console.log(`submit 버튼이 클릭이 됨 ${name}, ${age}`);
                console.log(`==>${ssn}, ${email}, ${phone}`);
                //객체 생성 newPerson 생성해서 , people 에 저장을 한다.
                // nextId 에도 1씩 증가 
                const newPerson = {
                    id: nextId,
                    name,
                    age,
                    ssn,
                    email,
                    phone
                }
                setPeople([...people, newPerson]);
                setNextId(nextId + 1);
                setName('');
                setAge('');
                setSsn('');
                setEmail('');
                setPhone('');
            }, [name, age, ssn, email, phone, nextId]
        );

    const peopleRows = useMemo(() => {
        console.log("people map useMemo 적용됨!");
        setChcolor(true);
        return people.map(person => (
            <tr key={person.id}>
                <td style={{ border: '1px solid #ddd', padding: '8px' }}>
                    {person.name}</td>
                <td style={{ border: '1px solid #ddd', padding: '8px' }}>{person.age}</td>
                <td style={{ border: '1px solid #ddd', padding: '8px' }}>{person.ssn}</td>
                <td style={{ border: '1px solid #ddd', padding: '8px' }}>{person.email}</td>
                <td style={{ border: '1px solid #ddd', padding: '8px' }}>{person.phone}</td>
            </tr>
        ));
    }, [people]);
    //React.ChangeEvent<HTMLInputElement>
    const changeData = (e: React.ChangeEvent<HTMLInputElement>) => {
        setBgcolors(e.target.value);
        console.log(`색상값 => ${e.target.value}`)

    }
    return (
        <div
            style={{
                maxWidth: '600px',
                margin: '30px auto',
                backgroundColor: bgcolors,
            }}
        >
            <form
                onSubmit={sendSubmit}
                style={{
                    display: 'flex',
                    flexDirection: 'column',
                    gap: '10px',
                    marginBottom: '20px',
                }}
            >
                <input
                    type="text"
                    value={name}
                    onChange={e => setName(e.target.value)}
                    placeholder="이름"
                    required
                />
                <input
                    type="number"
                    value={age}
                    onChange={e => setAge(e.target.value)}
                    placeholder="나이"
                />
                <input
                    type="text"
                    value={ssn}
                    onChange={e => setSsn(e.target.value)}
                    placeholder="주민번호 앞 6자리 + 뒤 1자리"
                    pattern="\d{7}"
                    maxLength={7}
                />
                <input
                    type="email"
                    value={email}
                    onChange={e => setEmail(e.target.value)}
                    placeholder="이메일"
                />
                <input
                    type="tel"
                    value={phone}
                    onChange={e => setPhone(e.target.value)}
                    placeholder="전화번호"
                />
                <button
                    type="submit"
                    style={{
                        backgroundColor: 'skyblue',
                        border: 'none',
                        padding: '10px',
                        cursor: 'pointer',
                    }}
                >
                    추가
                </button>
            </form>

            <table style={{ width: '100%', borderCollapse: 'collapse' }}>
                <thead>
                    <tr style={{ backgroundColor: 'pink', color: '#fff' }}>
                        <th style={{ border: '1px solid #ddd', padding: '8px' }}>이름</th>
                        <th style={{ border: '1px solid #ddd', padding: '8px' }}>나이</th>
                        <th style={{ border: '1px solid #ddd', padding: '8px' }}>주민번호</th>
                        <th style={{ border: '1px solid #ddd', padding: '8px' }}>이메일</th>
                        <th style={{ border: '1px solid #ddd', padding: '8px' }}>연락처</th>
                    </tr>
                </thead>
                <tbody
                    style={{
                        backgroundColor: chcolor ? 'red' : 'white',
                        transition: 'background-color 0.5s',
                    }}
                >
                    {peopleRows}
                </tbody>
                <tfoot>
                    <tr style={{ backgroundColor: 'pink', color: '#fff' }}>
                        <th colSpan={5}>
                            <input type="color" onChange={changeData} />
                        </th>
                    </tr>
                </tfoot>
            </table>
        </div>
    )
}

export default Ex2_PersonUseCallBack