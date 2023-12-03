export default function ProjectSidebar({handleBodyComponent, handleCreateNewRepository, handleCreateNewCommit}) {
    return(
        <aside id="sidebar">
            <button onClick={handleBodyComponent}>Your Repositories</button>
            <button onClick={handleCreateNewRepository}>Create a new Repository</button>
            <button onClick={handleCreateNewCommit}>Create a new Commit</button>
        </aside>
    );
}