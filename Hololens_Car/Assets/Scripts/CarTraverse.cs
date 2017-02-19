using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CarTraverse : MonoBehaviour {
	public float amplitude;
	public int level;
	public float forwardSpeed;
	public float turnSpeed;
	public float obstacleFrequency;
	public GameObject prefabCar;
	public GameObject glowParticles;
	const int STATE_INIT = 0;
	const int STATE_FORWARD = 1;
	const int STATE_TURN = 2;
	const int STATE_END = 3;
	private int state;

	// Use this for initialization
	void Start () {
		state = STATE_TURN;
		StartCoroutine(dropObjects (obstacleFrequency));
	}
	
	// Update is called once per frame
	void Update () {
		bobUpAndDown ();
		Instantiate (glowParticles, this.transform.position, Quaternion.identity);
		switch (state) {
		case (STATE_FORWARD): 
			moveForward (forwardSpeed);
			break;
		case (STATE_TURN):
			moveTurn (turnSpeed);
			break;
		default:
			break;
		}
	}
	void bobUpAndDown () {
		gameObject.transform.Translate(0,  Mathf.Cos(Time.time) * amplitude, 0);
		gameObject.transform.Rotate (4 * Mathf.Cos (Time.time - 2f) * amplitude, 0, 0);
	}

	IEnumerator dropObjects (float frequency) {
		for(;;) {
			Instantiate (prefabCar, (new Vector3(this.transform.position.x + Random.value-0.5f, -0.5f,this.transform.position.z + Random.value-0.5f)), Quaternion.Euler(new Vector3(-90, 0, 0))); 
			switch(state) {
			case STATE_FORWARD:
				state = STATE_TURN;
				break;
			case STATE_TURN:
				state = STATE_FORWARD;
				break;
			default:
				state = STATE_FORWARD;
				break;
			}
			yield return new WaitForSeconds (1f / frequency);
		}
	}

	void moveForward(float speed) {
		gameObject.transform.Translate (0, 0, speed * Time.deltaTime);
	}

	void moveTurn(float speed) {
		gameObject.transform.Rotate (0, speed * Time.deltaTime * 180, 0);
		gameObject.transform.Translate (0, 0, speed * Time.deltaTime);
	}
}
