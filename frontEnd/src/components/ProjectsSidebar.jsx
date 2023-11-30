export default function ProjectSidebar({handleBodyComponent, handleCreateNewCommit}) {
    return(
        <aside id="sidebar">
            <button onClick={handleBodyComponent}>Your Repositories</button>
            <button onClick={handleCreateNewCommit}>Create a new Repository</button>
        </aside>
    );
}