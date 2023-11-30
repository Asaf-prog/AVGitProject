import ShowGitFile from "./ShowGitFile.jsx";

export default function ShowChildren({treeNode}) {
    return(
        <>
        <ShowGitFile treeNode={treeNode.gitFileDTOList}/>
        </>
    );
}