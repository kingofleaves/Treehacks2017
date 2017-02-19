using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ScoreHandler : MonoBehaviour {
	public GameObject gameOverUI;
	private bool isGameOver;
	// Use this for initialization
	void Start () {
		isGameOver = false;
	}

	// Update is called once per frame
	void Update () {
		this.gameObject.transform.position = Camera.current.transform.position;
		Vector3 distance = (this.gameObject.transform.position - GameObject.FindWithTag ("Fairy").transform.position);
		if (distance.magnitude > 2) {
			loseHealth ();
		}
	}
	void OnCollisionEnter (Collision col) {
		Debug.Log ("hit");
		if (col.gameObject.tag == "Obstacle") {
			loseHealth ();
		}
	}

	void loseHealth() {
		if (!isGameOver) {
			gameOverUI.SetActive (true);
			isGameOver = true;
		}
	}
}


