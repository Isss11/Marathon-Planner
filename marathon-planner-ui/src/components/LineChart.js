// components/LineChart.js
import React from "react";
import { Line } from "react-chartjs-2";
function LineChart({ chartData }) {
    return (
        <div className="chart-container">
            <Line
                data={chartData}
                options={{
                    plugins: {
                        legend: {
                            display: false
                        },

                    },
                    scales: {
                        x: {
                            title: {
                                display: true,
                                text: "Day",
                                color: "black",
                            },
                        },
                        y: {
                            title: {
                                display: true,
                                text: "Distance",
                                color: "black",
                            },
                        }
                    }
                }}

            />
        </div>
    );
}
export default LineChart;