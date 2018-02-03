package com.f3.aop;

public class AspectJExpressionPointcutAdvisor  implements PointcutAdvior{

	 private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

	    private Advice advice;

	    public void setAdvice(Advice advice) {
	        this.advice = advice;
	    }

	    public void setExpression(String expression) {
	        this.pointcut.setExpression(expression);
	    }

		@Override
		public Advice getAdvice() {
			return advice;
		}

	    @Override
		public Pointcut getPointcut() {
			return pointcut;
		}

}
