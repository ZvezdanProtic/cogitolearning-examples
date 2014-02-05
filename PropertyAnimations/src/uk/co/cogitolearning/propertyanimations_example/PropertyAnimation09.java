package uk.co.cogitolearning.propertyanimations_example;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PropertyAnimation09 extends Activity
{

  private AnimatorSet anim;
  private TextView fpsText;

  @Override
  @SuppressLint("NewApi")
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.property_animations09);

    if (android.os.Build.VERSION.SDK_INT >= 19)
    {

      fpsText = (TextView) findViewById(R.id.fps_text);

      ImageView someImage = (ImageView) findViewById(R.id.some_image);

      ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(someImage, "rotation", 0, 360);
      rotateAnim.setDuration(1000);
      rotateAnim.setRepeatCount(5);
      rotateAnim.setRepeatMode(ObjectAnimator.RESTART);

      TimeAnimator.TimeListener listener = new FpsTimeListener(fpsText);
      final TimeAnimator timeAnim = new TimeAnimator();
      timeAnim.setTimeListener(listener);
      timeAnim.setDuration(1000);
      anim = new AnimatorSet();
      anim.play(rotateAnim).with(timeAnim);
      rotateAnim.addListener(new Animator.AnimatorListener()
      {
        
        @Override
        public void onAnimationStart(Animator animation) {}
        
        @Override
        public void onAnimationRepeat(Animator animation) {}
        
        @Override
        public void onAnimationEnd(Animator animation)
        {
          
          timeAnim.end(); 
        }
        
        @Override
        public void onAnimationCancel(Animator animation) {}
      });
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  public void startAnimation(View view)
  {
    anim.start();
  }

}
