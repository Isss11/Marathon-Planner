const RunnerForm = () => {
    return (
        <div>
            <h2>Runner Information</h2>
            <form>
                <label for="skillLevel">Skill Level</label>
                <input type="range" min="1" max="10" step="1" id="skillLevel" />
                <br></br>
                <label for="weeklyIncrease">Weekly Increase</label>
                <input type="range" min="1" max="30" step="1" id="weeklyIncrease" />
                <br></br>
                <label for="metricDisabled">Use Miles</label>
                <input type="checkbox" id="metricDisabled" name="metricDisabled" value="metricDisabled" />
            </form>
        </div>
    )
}

export default RunnerForm;