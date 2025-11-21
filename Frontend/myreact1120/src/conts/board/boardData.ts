//boardData.ts 
//이 또한 더미 데이터이고 임시 데이터이다.
// 게시판의 데이터 객체를 선언 => BoardVO 
export interface BoardItem {
    id: number;
    title: string;
    writer: string;
    content: string;
}
// Array.from으로 
// [{id:1,title:'테스'},{},...] = 31개가 들어 갈것이다.
export const boardList: BoardItem[] = Array.from({ length: 31 }, (_, i) => (
    {
        id: i + 1,
        title:`테스형이 작성한 글 ${i+1}`,
        writer: `테스형${i+1}`,
        content: `테스형님이 작성한 글 ${i + 1} 임`,
    }
));