import Schedule from "./Schedule";

const TrainingPlan = ({ trainingPlan, pdfOnClick, isMetric }) => {
    return (
        <div>
            <h2><strong><em>Your</em> Training Plan</strong></h2>
            {trainingPlan.length > 0 ? (
                <div>
                    {/* <TrainingPlanGraph /> */}
                    {/* <button className="btn btn-danger" onClick={pdfOnClick}>Save as a PDF</button> */}
                    <Schedule trainingPlan={trainingPlan} isMetric={isMetric}></Schedule>
                    <hr></hr>
                    <p>After all of this training, you will be ready to run your marathon!</p>
                </div>
            ) : (
                <div>
                    <p>Click 'Generate Training Plan' above to create a customized training plan.</p>
                </div>)
            }
        </div>
    )
}

export default TrainingPlan;