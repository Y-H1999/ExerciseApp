package com.example.bottomnav.food_mng;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

class ModelViewerView extends GLSurfaceView implements GestureDetector.OnGestureListener {

    // メンバー変数
    private OpenGLPickRenderer m_renderer;
    private GestureDetector	m_gesturedetector;	// 長押し用

    // コンストラクタ
    public ModelViewerView(Context context, AttributeSet attrs )
    {
        super( context );

        // Rendererの作成
        m_renderer = new OpenGLPickRenderer();

        // GLSurfaceViewにRendererをセット
        setRenderer( m_renderer );

        // 絶え間ないレンダリングではなく都度のレンダリング（setRenderer()よりも後に呼び出す必要あり）
        setRenderMode( GLSurfaceView.RENDERMODE_WHEN_DIRTY );

        // モデルの作成および登録
        m_renderer.setModel( makeModel() );

        // GestureDetectorの作成
        m_gesturedetector = new GestureDetector( context, (GestureDetector.OnGestureListener) this);

    }


    @Override
    public boolean onTouchEvent( MotionEvent event )
    {
        // ジェスチャ処理
        if( m_gesturedetector.onTouchEvent( event ) )
        {
            return true;
        }

        int         action     = event.getAction();
        int         pointcount = event.getPointerCount();
        final float fX         = event.getX();
        final float fY         = event.getY();

        switch( action & MotionEvent.ACTION_MASK )
        {
            // トラッキングの開始
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if( 1 == pointcount )
                { // １つの指でドラッグ
                    // １本指ドラッグで行うトラッキングモードを指定する
                    m_renderer.beginTracking( fX, fY, OpenGLTrackRenderer.ETrackingMode.TM_ROTATE );

                }
                else if( 2 == pointcount )
                { // ２つの指でドラッグ
                    float       fX1       = event.getX( 1 );
                    float       fY1       = event.getY( 1 );
                    final float fDistance = (float)Math.sqrt( ( fX1 - fX ) * ( fX1 - fX ) + ( fY1 - fY ) * ( fY1 - fY ) );
                    m_renderer.beginTracking( -fDistance, -fDistance, OpenGLTrackRenderer.ETrackingMode.TM_ZOOM );
                }
                break;
            // トラッキングの終了
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                m_renderer.endTracking();
                break;
            // トラッキング
            case MotionEvent.ACTION_MOVE:
                if( 2 == pointcount
                        && OpenGLTrackRenderer.ETrackingMode.TM_ZOOM == m_renderer.getTrackingMode() )
                { // ズームは、１つ目の指の座標値ではなく、２つの指の距離で処理する。
                    float       fX1       = event.getX( 1 );
                    float       fY1       = event.getY( 1 );
                    final float fDistance = (float)Math.sqrt( ( fX1 - fX ) * ( fX1 - fX ) + ( fY1 - fY ) * ( fY1 - fY ) );
                    m_renderer.doTracking( -fDistance, -fDistance );
                    requestRender(); // 再描画
                }
                else
                { // 通常は、１つ目の指の座標値で処理する。
                    m_renderer.doTracking( fX, fY );
                    requestRender(); // 再描画
                }
                break;
        }

        return true;
    }

    // モデル作成
    private Model makeModel()
    {
        float[] afVertex = {
                -25.0f, -25.0f, -25.0f,
                25.0f, -25.0f, -25.0f,
                -25.0f, 25.0f, -25.0f,

                25.0f, -25.0f, -25.0f,
                25.0f, 25.0f, -25.0f,
                -25.0f, 25.0f, -25.0f,

                25.0f, -25.0f, -25.0f,
                25.0f, -25.0f, 25.0f,
                25.0f, 25.0f, -25.0f,

                25.0f, -25.0f, 25.0f,
                25.0f, 25.0f, 25.0f,
                25.0f, 25.0f, -25.0f,

                25.0f, -25.0f, 25.0f,
                -25.0f, -25.0f, 25.0f,
                25.0f, 25.0f, 25.0f,

                -25.0f, -25.0f, 25.0f,
                -25.0f, 25.0f, 25.0f,
                25.0f, 25.0f, 25.0f,

                -25.0f, -25.0f, 25.0f,
                -25.0f, -25.0f, -25.0f,
                -25.0f, 25.0f, 25.0f,

                -25.0f, -25.0f, -25.0f,
                -25.0f, 25.0f, -25.0f,
                -25.0f, 25.0f, 25.0f,

                -25.0f, 25.0f, -25.0f,
                25.0f, 25.0f, -25.0f,
                -25.0f, 25.0f, 25.0f,

                25.0f, 25.0f, -25.0f,
                25.0f, 25.0f, 25.0f,
                -25.0f, 25.0f, 25.0f,

                -25.0f, -25.0f, 25.0f,
                25.0f, -25.0f, 25.0f,
                -25.0f, -25.0f, -25.0f,

                25.0f, -25.0f, 25.0f,
                25.0f, -25.0f, -25.0f,
                -25.0f, -25.0f, -25.0f };


        return new Model( afVertex );


    }

    @Override
    public boolean onDown( MotionEvent e )
    {
        return false;
    }

    @Override
    public void onShowPress( MotionEvent e )
    {

    }

    @Override
    public boolean onSingleTapUp( MotionEvent e )
    {
        return false;
    }

    @Override
    public boolean onScroll( MotionEvent e1, MotionEvent e2, float distanceX, float distanceY )
    {
        return false;
    }

    @Override
    public void onLongPress( MotionEvent e )
    {
        final float fX = e.getX();
        final float fY = e.getY();
        queueEvent( new Runnable()
        {
            public void run()
            {
                if( m_renderer.doPicking( fX, fY ) )
                {
                    requestRender(); // 再描画
                }
            }
        } );
    }

    @Override
    public boolean onFling( MotionEvent e1, MotionEvent e2, float velocityX, float velocityY )
    {
        return false;
    }

}
