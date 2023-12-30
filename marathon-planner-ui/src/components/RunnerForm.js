const RunnerForm = ({ onClick, skillLevel, setSkillLevel, weeklyIncrease, setWeeklyIncrease, useMiles, setUseMiles }) => {
    const isWeeklyIncreaseHigh = () => {
        return weeklyIncrease > 10;
    }

    const getRunnerSkill = () => {
        if (skillLevel < 3) {
            return "Beginner";
        } else if (skillLevel < 7) {
            return "Intermediate";
        } else {
            return "Advanced";
        }
    }

    return (
        <div>
            <h2>Runner Information</h2>
            <form>
                <label htmlFor="skillLevel">Skill Level</label>
                <br></br>
                <input className="form-range" type="range" min="1" max="10" step="1" value={skillLevel} onChange={setSkillLevel} id="skillLevel" />
                <br></br>
                <label htmlFor="weeklyIncrease">Weekly Increase<br></br></label>
                <br></br>
                <input className="form-range" type="range" min="1" max="30" step="1" value={weeklyIncrease} onChange={setWeeklyIncrease}
                    id="weeklyIncrease" />
                <br></br>
                <div className="form-check">
                    <label className="form-check-label" htmlFor="metricDisabled">Use Miles</label>
                    <input className="form-check-input" type="checkbox" id="metricDisabled" name="metricDisabled" checked={useMiles} onChange={setUseMiles} />
                </div>
                <br></br>

                <div>
                    <div><strong>Skill Level:</strong> {skillLevel}/10 ({getRunnerSkill()})</div>
                    <div><strong>Distance Increase Each Week:</strong> {weeklyIncrease}%</div>
                    {isWeeklyIncreaseHigh() && <div>Increasing distance by over 10% a week is is unsafe. Be careful!</div>}
                </div>

                <br></br>

                <button className="btn btn-primary" onClick={onClick}>Generate Training Plan</button>
            </form>
        </div>
    )
}

export default RunnerForm;