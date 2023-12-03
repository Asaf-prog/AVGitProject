import ShowGitFile from "./ShowGitFile.jsx";

export default function ShowChildren({treeNode}) {
    return(
        <div>
            {treeNode.children.length === 0 ? null : treeNode.children.map((item,index) => (
                <ShowGitFile
                key={index}
                treeNode={item.gitFileDTOList}
                />
            )) }
        </div>
    );
}
