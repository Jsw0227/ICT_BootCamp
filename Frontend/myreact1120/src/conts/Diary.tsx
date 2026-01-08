import React from 'react'
import HTMLFlipBook from 'react-pageflip';

interface MyBookProps {
    //책디자인의 너비와 높이 (필수)
    width?: number;
    height?: number;
    style?: React.CSSProperties;
    className?: string; //우리가 만들 클래스 속성 적용
    //showCover 가 true이면 첫번째 페이지를 표지로 사용하겠다 
    showCover?: boolean;
    autoSize?: boolean;
    //페이지를 넘길때 그림자의 투명도값 (기본값 1 , 0 ~ 1)
    maxShadowOpacity?: number;
    mobileScrollSupport?: boolean;// 모바일 장치에서 스크롤로 넘길 것이냐
}
const myData = [
    {
        image: "images/yujimin01.png",
        movieimg: "images/yujimin01.png",
        text: "오늘은 강가를 걸었다. 물소리가 너무 좋았다.\n 정말 재미 있었다.아! 행복하다.",
        title: "영화1"
    },
    {
        image: "images/yujimin02.png",
        movieimg: "images/yujimin02.png",
        text: "밤에 거리를 걸어 보았다. 하루가 정말 보람되었다.",
        title: "영화2"
    },
    {
        image: "images/bg3.png",
        movieimg: "images/yujimin03.png",
        text: "도서관에서 책을 읽었다. 마음이 차분해졌다.",
        title: "영화3"
    },
    {
        image: "images/bg4.png",
        movieimg: "images/yujimin03.jpg",
        text: "오늘은 비가 내렸다. 그래도 복습은 열심히 했다.",
        title: "영화4"
    },
    {
        image: "images/bg5.png",
        movieimg: "images/yujimin05.png",
        text: "오늘은 비가 내렸다. 내일도 복습은 열심히 하겠다.",
        title: "영화5"
    }
]

const Diary: React.FC = () => {
    return (
        <div style={{ textAlign: 'center', marginTop: '30px' }}>
            <h2>Diary</h2>
            <div style={{
                width: '620px', margin: '20px auto',
                overflow: 'hidden', borderRadius: '10px',
                boxShadow: '0 8px 20px rgba(0,0,0,0.2)'
            }}>
                {/* usePortrait={true} : 모바일에서 화면이 작으면 책이 한장(반응형웹) 
              {...({ style: {}, usePortrait: true } as any)} 
              기존의 스타일 인터페이스에 동적으로 추가 하기  
             */}
                <HTMLFlipBook width={300} height={400}
                    showCover={true}
                    {...({ style: {}, usePortrait: true } as any)}
                    autoSize={true} mobileScrollSupport={true}
                    maxShadowOpacity={0.2} usePortrait={true}
                    style={{ borderRadius: '10px' }}
                >
                    {/* myData에서 flatMap 사용해서 데이터를 반복 배치하기 
                1. 즉시 실행함수 를 선언한다.(function(){})()
                ,(() => ))()
                HTMLFlipBook 에 데이터를 배치한다.
            */}
                    {
                        (() => myData.flatMap((entry, idx) => [
                            //이미지 페이지
                            <div key={`img-${idx}`} style={{
                                width: '100%',
                                height: '100%',
                                backgroundColor: '#fff',
                                display: 'flex',
                                justifyContent: 'center',
                                alignItems: 'center',
                                overflow: 'hidden',
                            }}>
                                <img src={entry.image} alt={`Diary Image ${idx + 1}`} style={{ width: '100%', height: '100%', objectFit: 'cover' }} />
                            </div>,

                            //텍스트 페이지
                            <div key={`txt-${idx}`} 
                            style={{ width: '100%', height: '100%', backgroundColor: '#fff', display: 'flex', flexDirection: 'column', padding: '20px', boxSizing: 'border-box', }}> 
                     
                            <div style={{ display: 'flex', alignItems: 'center', marginBottom: '15px' }}> 
                     
                                <img src={entry.movieimg} alt="movie" style={{ width: '120px', height: '160px', objectFit: 'cover', borderRadius: '8px', marginRight: '10px', marginTop:'20px', marginLeft:'20px' }} /> 
           
                                <h3 style={{ margin: 0, fontSize: '20px', fontWeight: 'bold', textAlign: 'left' }}> {entry.title} </h3> 
                            </div> 
                                <div 
                                    style={{
                                        backgroundColor: '#f2f2f2',  
                                        borderRadius: '10px',        
                                        padding: '15px',             
                                        marginTop: '10px',  marginLeft:'5px', marginRight:'5px' ,    
                                        boxShadow: '0 2px 6px rgba(0,0,0,0.1)'
                                    }} >
                                <p style={{ whiteSpace: 'pre-line', fontSize: '16px', lineHeight: '1.5', textAlign: 'left', margin: 0 }}> {entry.text} </p> 

                                </div>
                            </div>

                        ]))()

                    }
                </HTMLFlipBook>
            </div>
        </div>
    )
}

export default Diary