const RunnerForm = ({ onClick, skillLevel, setSkillLevel, weeklyIncrease, setWeeklyIncrease, useMiles, setUseMiles }) => {
    return (
        <div>
            <h2>Runner Information</h2>
            <form>
                <label htmlFor="skillLevel">Skill Level</label>
                <input type="range" min="1" max="10" step="1" value={skillLevel} onChange={setSkillLevel} id="skillLevel" />
                <br></br>
                <label htmlFor="weeklyIncrease">Weekly Increase</label>
                <input type="range" min="1" max="30" step="1" value={weeklyIncrease} onChange={setWeeklyIncrease}
                    id="weeklyIncrease" />
                <br></br>
                <label htmlFor="metricDisabled">Use Miles</label>
                <input type="checkbox" id="metricDisabled" name="metricDisabled" checked={useMiles} onChange={setUseMiles} />
                <br></br>
                <button onClick={onClick}>Generate Training Plan</button>
            </form>
        </div>
    )
}

export default RunnerForm;