package org.krohm.wicket.component.charts.jfreechart;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.krohm.wicket.component.charts.jfreechart.util.AbstractChartImage;

/**
 *
 * @author arnaud
 */

public abstract class JfcPieChart extends AbstractChartImage {

    protected int height = 250;
    protected int width = 250;
    protected boolean legend = true;
    protected Font font = new Font("SansSerif", Font.PLAIN, 12);
    protected String title = "";
    protected float transparency = 1F;
    protected List<Color> colorList = null;

    public JfcPieChart(String id) {
        super(id);
    }

    // Data Required for a Pie
    public abstract Map<String, Number> getData();

    @Override
    public final JFreeChart getJFreeChart() {

        Map<String, Number> dataMap = this.getData();
        DefaultPieDataset dataSet = new DefaultPieDataset();

        for (String key : dataMap.keySet()) {
            dataSet.setValue(key, dataMap.get(key));
        }

        JFreeChart jfc = ChartFactory.createPieChart(getTitle(), dataSet, getLegend(), true, false);
        PiePlot pp = (PiePlot) jfc.getPlot();

        // Default options, sould be used as properties
        pp.setSectionOutlinesVisible(false);
        pp.setLabelFont(getFont());
        pp.setCircular(false);
        pp.setLabelGap(0.02);

        // Manages the colors
        if (colorList != null) {
            //TODO
        }

        // Manages Transparency
        pp.setForegroundAlpha(transparency);
        return jfc;
    }

    //
    // BeanLike Abilities
    //
    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getTitle() {
        return title;
    }

    public Font getFont() {
        return font;
    }

    public boolean getLegend() {
        return legend;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setLegend(boolean legend) {
        this.legend = legend;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getTransparency() {
        return transparency;
    }

    public void setTransparency(float transparency) {
        this.transparency = transparency;
    }

    public List<Color> getColorList() {
        return colorList;
    }

    public void setColorList(List<Color> colorList) {
        this.colorList = colorList;
    }
}
