/*
 * source:
 * http://javabeanz.wordpress.com/2007/07/04/creating-barcharts-with-custom-colours-using-jfreechart/
 * 
 */

package taskmanager.view;

import org.jfree.chart.renderer.category.BarRenderer;
import java.awt.Color;
import java.awt.Paint;
class CustomRenderer extends BarRenderer
{
 private Paint[] colors;
 public CustomRenderer()
 {
    this.colors = new Paint[] {Color.red, Color.blue, Color.green,
      Color.orange, Color.magenta};
 }
 public Paint getItemPaint(final int row, final int column)
 {
    // returns color for each column
    return (this.colors[column % this.colors.length]);
 }
}