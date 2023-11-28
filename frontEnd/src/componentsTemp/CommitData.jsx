export default function CommitData({commitContent, commitSh1,commitData,commitAuthor}){
    return (
        <>
        <p>{commitContent}</p>
        <p>{commitSh1}</p>
        <p>{commitData}</p>
        <p>{commitAuthor}</p>
        </>
    );
}